package com.example.bookservice.repository;


import com.example.bookservice.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List <Book> findAll();
    @Transactional
    void deleteByCode(int code);
}