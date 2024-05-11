package org.example.userservice.controller;

import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/main")
public class LkController {
    @Autowired
    UserService userService;
    @GetMapping("/lk")
    public String getUser()
    {
        return "LK Users:"+ userService.findAll();
    }
    @GetMapping("/lk/userid={id}")
    public String getTest(@PathVariable("id") int id)
    {
        return userService.getAllBooksByUserId(id).toString();
    }
}
