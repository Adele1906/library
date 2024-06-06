package com.example.gateway_service.testauth.tst;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormularRepository extends CrudRepository<BookFormular, Integer> {
    List<BookFormular> findAll();
}
