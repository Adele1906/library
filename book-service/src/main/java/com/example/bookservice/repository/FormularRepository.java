package com.example.bookservice.repository;

import com.example.bookservice.model.BookFormular;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularRepository extends CrudRepository<BookFormular, Integer> {

}
