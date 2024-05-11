package com.example.reportservice.service;
import com.example.reportservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportService {
    @Autowired
    Repo repository;

    public List<Book> bookReport (){

        return repository.getAllPopularBook();
    }

}
