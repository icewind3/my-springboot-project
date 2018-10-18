package org.icewind.elasticsearchdemo.service;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.icewind.elasticsearchdemo.dao.DztFwDao;
import org.icewind.elasticsearchdemo.entity.DztFw;
import org.icewind.elasticsearchdemo.repository.DztFwRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Ye Jianyu
 * @date 2018/9/30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DztFwServiceImpl {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DztFwDao dztFwDao;

    @Autowired
    private DztFwRepository dztFwRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    public List<DztFw> createDzDoc() {
        List<DztFw> dztFwList = dztFwRepository.findAll();
        return Lists.newArrayList(dztFwDao.saveAll(dztFwList));
    }

    public List<DztFw> query(String keyword, String ssxq, Integer page, Integer size) {

        // 分页参数
        Pageable pageable = PageRequest.of(page, size);

        // 分数，并自动按分排序
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.wildcardQuery("dzmc", "*" + keyword + "*"))
                .should(QueryBuilders.wildcardQuery("dzpy", "*" + keyword + "*"))
                .must(QueryBuilders.wildcardQuery("sssqjcwhdm", ssxq + "*"))
                .minimumShouldMatch(1);

        // 分数、分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(queryBuilder).build();

        try {
            Page<DztFw> searchPageResults = dztFwDao.search(searchQuery);
            return searchPageResults.getContent();
        } catch (Exception e) {
            logger.error("从elstic查询标准地址出错", e);
        }
        return Lists.newArrayList();
    }

    public List<DztFw> query2(String keyword, String ssxq, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.wildcardQuery("dzmc", "*" + keyword + "*"))
                .should(QueryBuilders.wildcardQuery("dzpy", "*" + keyword + "*"))
                .must(QueryBuilders.wildcardQuery("sssqjcwhdm", ssxq + "*"))
                .minimumShouldMatch(1);

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withIndices("dz2018").withTypes("fw")
                .withQuery(queryBuilder).build();

        List<DztFw> dztFwList = elasticsearchTemplate.queryForList(searchQuery, DztFw.class);
        return dztFwList.stream().sorted((fw1, fw2) ->
        {
            String qc1 = fw1.getDzmc();
            String qc2 = fw2.getDzmc();
            if (qc1.equals(qc2)) {
                return 0;
            }
            if (null == qc2) {
                return -1;
            }
            qc1 = handleQcDigit(qc1);
            qc2 = handleQcDigit(qc2);
            return qc1.compareTo(qc2);

        }).collect(Collectors.toList());
    }

    private String handleQcDigit(String qc) {
        String digit;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(qc);
        int pos = 0;
        StringBuilder strBuf = new StringBuilder();
        while (matcher.find()) {
            digit = matcher.group();
            strBuf.append(qc, pos, matcher.start());
            if (digit.length() < 5) {
                digit = StringUtils.leftPad(digit, 5);
            }
            strBuf.append(digit);
            pos = matcher.end();
        }
        strBuf.append(qc.substring(pos));
        return strBuf.toString();
    }
}
