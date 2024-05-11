package com.example.bookservice.controller;

import com.example.bookservice.model.BookFormular;
import com.example.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/books")
     public String showAllBooks(Model model){
        model.addAttribute("allBooks",bookService.getAllBooks());
         return "books";
     }

    @GetMapping("/addbook")
    public String formBook(Model model){
        return "formNewBook";
    }

    @PostMapping("/addbook")
    public String addNewBook(@RequestParam int code,@RequestParam String name, @RequestParam String description){
        bookService.addBook(code,name,description);
        return "redirect:/books";
    }
    @GetMapping("/deletebook")
    public String formDeleteBook(Model model){
        return "formDeleteBook";
    }
    @PostMapping("/deletebook")
    public String deleteBook(@RequestParam int code1){
        bookService.deleteBook(code1);
        return "redirect:/books";
    }
    @GetMapping("/addFormular")
    public String addReportInFormular(Model model){
        return "formBookFormular";
    }
    @PostMapping("/addFormular")
    public String sendForm(@RequestParam int code, @RequestParam int user_id){
        bookService.addReportInFormular(code,user_id);
        return "redirect:/books";
    }
    @GetMapping("/formulars")
    public String showAllFormulars(Model model) {
        List<BookFormular> listFormulars = bookService.getAllFormulars();
        model.addAttribute("formulars",listFormulars);
        return "formulars";
    }

    @GetMapping("/formulars/setStatusById={id}")
    public String deleteProducts(@PathVariable("id") int id, Model model) {

        bookService.updateStatusReturn(id);
        return "redirect:/formulars";
    }


}
