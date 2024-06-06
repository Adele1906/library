package com.example.gateway_service.testauth.tst;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List <Book> findAll();
    @Transactional
    void deleteByCode(int code);

    Optional<Book> findByCode(int code);

    //List<Book> getBooksByUserId(String userId);
}