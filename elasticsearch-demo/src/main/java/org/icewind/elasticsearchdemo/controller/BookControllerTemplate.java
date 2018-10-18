package org.icewind.elasticsearchdemo.controller;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.icewind.elasticsearchdemo.entity.Book;
import org.icewind.elasticsearchdemo.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Ye Jianyu
 * @date 2018/8/2
 */
@RestController
@RequestMapping("/template")
public class BookControllerTemplate {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/all")
    public List<Map<String, Object>> searchAll() throws Exception {
        Client client = elasticsearchTemplate.getClient();

        SearchRequestBuilder srb = client.prepareSearch("product").setTypes("book");
        SearchResponse sr = srb.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
        SearchHits hits = sr.getHits();
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSource();
            list.add(source);
            System.out.println(hit.getSourceAsString());
        }
        return list;
    }

//    @GetMapping("/all")
//    public List<Book> searchAll(){
//        NativeSearchQueryBuilder nbq = new NativeSearchQueryBuilder()
//                .withIndices("product").withTypes("book")
//                .withSearchType(SearchType.DEFAULT);
//
//        QueryBuilder qb = QueryBuilders.matchAllQuery();
//        return elasticsearchTemplate.queryForList(nbq.withQuery(qb).build(), Book.class);
//    }

    @GetMapping("/message/{page}/{size}/{q}")
    public List<Book> searchAll(@PathVariable Integer page, @PathVariable Integer size,
                                @PathVariable String q) {

        Pageable pageable = PageRequest.of(page,size);

        NativeSearchQueryBuilder nbq = new NativeSearchQueryBuilder()
                .withIndices("product").withTypes("book")
                .withSearchType(SearchType.DEFAULT);

        QueryBuilder qb = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchPhrasePrefixQuery("message", q));
//                .must(QueryBuilders.matchPhrasePrefixQuery("name", q));
//            bqb.must(QueryBuilders.termQuery("message",q));
//            bqb.must(QueryBuilders.matchQuery("message",q));
//            bqb.must(QueryBuilders.matchPhraseQuery("message",q));

//            bqb.filter(QueryBuilders.termQuery("name","bk"));

        Client client = elasticsearchTemplate.getClient();
        SearchRequestBuilder srb = client.prepareSearch("product").setTypes("book");
        HighlightBuilder highlightBuilder = new HighlightBuilder().field("message").preTags("<span>").postTags("</span>");
        SearchResponse sr = srb.setQuery(qb).highlighter(highlightBuilder).execute().actionGet();
        SearchHits hits = sr.getHits();
        List<Book> result = new ArrayList<>();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSource();
            source.put("message",hit.getHighlightFields().get("message").getFragments()[0].toString());
            System.out.println(hit.getSourceAsString());
            System.out.println(Arrays.toString(hit.getHighlightFields().get("message").getFragments()));

            result.add(BeanUtils.mapToObject(source, Book.class));

        }
        return result;
//        Page<Object> message = elasticsearchTemplate.queryForPage(nbq.withQuery(qb)
//                .withHighlightFields(new HighlightBuilder.Field("message").preTags("<em>").postTags("</em>"))
//                .withPageable(pageable).build(), Object.class);
//        System.out.println("-----------");
//        System.out.println(message);
//        System.out.println("-----------");

//        return elasticsearchTemplate.queryForList(nbq.withQuery(qb)
//                .withHighlightFields()
//                .withPageable(pageable).build(), Book.class);
    }


}
