package org.icewind.elasticsearchdemo.dao;

import org.icewind.elasticsearchdemo.entity.DztFw;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Ye Jianyu
 * @date 2018/9/30
 */
public interface DztFwDao extends ElasticsearchRepository<DztFw, String> {
}
