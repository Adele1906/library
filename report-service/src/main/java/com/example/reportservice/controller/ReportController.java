package com.example.reportservice.controller;

import com.example.reportservice.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {
@Autowired
    ReportService reportService;
    @GetMapping("/report")
    public String showReport(){
        return reportService.bookReport().toString();
    }
}
