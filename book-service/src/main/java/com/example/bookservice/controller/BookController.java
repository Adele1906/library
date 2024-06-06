package com.example.bookservice.controller;

import com.example.bookservice.model.BookFormular;
import com.example.bookservice.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping(value={"", "/", "/showbooks"}, method= RequestMethod.GET)
    public String showbooks( Model model){
        model.addAttribute("books",bookService.getAllBooks());
        return "books";
    }
    @GetMapping("/addbook/id={id}")
    public String addbook(@CookieValue(value = "iddus") String iddus, @PathVariable("id") int id, Model model){
        bookService.addReportInFormular(id,iddus);
        addLog("user with id="+iddus+" get book with id="+id);
        return "books";
    }
    @GetMapping("/showformular")
    public String showformulars( @CookieValue(value = "iddus") String iddus, Model model){

        List<BookFormular> formulars= bookService.getAllFormulars();
        List<BookFormular> formularsByUser = new java.util.ArrayList<>(List.of());
        for(BookFormular formular:formulars){
            if(Objects.equals(formular.getUser_id(), iddus)){
                formularsByUser.add(formular);
            }
        }
        if(formularsByUser.isEmpty()){
            model.addAttribute("msg","Пока здесь нет книг...");
        } else model.addAttribute("formulars", formularsByUser);

        return "formulars";
    }
    @GetMapping("/returnbook/id={id}")
    public String returnbook(@CookieValue(value = "iddus") String iddus,@PathVariable("id") int idFormular, Model model){
        bookService.addReturnBookInFormular(idFormular);
        addLog("user with name="+iddus+" return book. Formular with id="+idFormular);
        return "formulars";
    }
    public void addLog(String audit){
        String auditUrl = "http://localhost:8087/addAudit";
        String logUrl = "http://localhost:8087/addLog";
        String log = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        HttpEntity<String> requestAddAudit = new HttpEntity<>(audit, headers);

        ResponseEntity<String> responseAddAudit = restTemplate.postForEntity(auditUrl, requestAddAudit, String.class);

        if (responseAddAudit.getStatusCode() == HttpStatus.OK) {
            log = "[INFO] Successfully added audit event";

        } else {
            log = "[INFO] Failed to add audit event";
        }

        HttpEntity<String> requestAddLog = new HttpEntity<>(log, headers);
        restTemplate.postForEntity(logUrl, requestAddLog, String.class);

    }
    /*
    @GetMapping("/addbook")
    public String formBook(Model model,@RequestHeader("Authorization") String token){

        return "formNewBook";
    }

    @PostMapping("/addbook")
    public String addNewBook(@RequestParam int code,@RequestParam String name, @RequestParam String description){
        bookService.addBook(code,name,description);
        return "redirect:http://localhost:8081/";
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

     */

}
