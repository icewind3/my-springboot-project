package org.icewind.elasticsearchdemo.dao;

import org.icewind.elasticsearchdemo.entity.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author Ye Jianyu
 * @date 2018/8/2
 */
public interface BookDao extends ElasticsearchRepository<Book, String> {

    List<Book> findAllByMessage(String message);
}
