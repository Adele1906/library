package com.example.reportservice.service;

import com.example.reportservice.model.Book;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repo extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT books.* FROM book_formular \n" +
            "JOIN books ON book_formular.book_code=books.code \n" +
            "GROUP BY book_code \n" +
            "ORDER BY COUNT(book_code) desc \n" +
            "LIMIT 10 ", nativeQuery = true)
    List <Book> getAllPopularBook();
}
