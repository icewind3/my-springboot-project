package org.icewind.elasticsearchdemo.controller;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.icewind.elasticsearchdemo.dao.BookDao;
import org.icewind.elasticsearchdemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author Ye Jianyu
 * @date 2018/8/2
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/get/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookDao.findById(id).orElse(null);
    }

    @GetMapping("/select/{q}")
    public List<Book> testSearch(@PathVariable String q) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
        Iterable<Book> searchResult = bookDao.search(builder);
        Iterator<Book> iterator = searchResult.iterator();
        List<Book> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    @GetMapping("/{page}/{size}/{q}")
    public List<Book> searchBook(@PathVariable Integer page, @PathVariable Integer size, @PathVariable String q) {

        // 分页参数
        Pageable pageable = PageRequest.of(page, size);

        // 分数，并自动按分排序
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", q),
                        ScoreFunctionBuilders.weightFactorFunction(100)))
                .should(QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("message", q),
                        ScoreFunctionBuilders.weightFactorFunction(10)));

        // 分数、分页
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable)
                .withQuery(queryBuilder).build();

        Page<Book> searchPageResults = bookDao.search(searchQuery);
        return searchPageResults.getContent();

    }

    @GetMapping("/test/msg/{q}")
    public List<Book> searchMsg(@PathVariable String q) {
        return bookDao.findAllByMessage(q);
    }

    @PostMapping("/insert")
    public Book insertBook(Book book) {
        bookDao.save(book);
        return book;
    }

    @DeleteMapping("/delete/{id}")
    public Book deleteBook(@PathVariable String id) {
        System.out.println("delete id:" + id);
        Optional<Book> optional = bookDao.findById(id);
        if (optional.isPresent()){
            bookDao.deleteById(id);
        }
        return optional.orElse(null);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        System.out.println("----- update book -----");
        System.out.println(book.toString());
        if (bookDao.existsById(book.getId())){
            bookDao.save(book);
        }
        return book;
    }
}
