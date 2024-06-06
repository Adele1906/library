package com.example.gateway_service.testauth;

import com.example.gateway_service.testauth.tst.BookFormular;
import com.example.gateway_service.testauth.tst.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.gateway_service.testauth.tst.BookService;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ctrl {
@Autowired
    BookService bookService;
@Autowired
    UserService userService;


    @GetMapping("/user/lk")
    public String lk(@AuthenticationPrincipal OAuth2User auth, Model model){
        model.addAttribute("user", auth.getAttribute("name"));
        addLog("user with name="+auth.getAttribute("name")+" visit lk");
        return "lk";
    }
    @GetMapping("/user/admin")
    public String adminpage(@AuthenticationPrincipal OAuth2User auth, Model model){
        model.addAttribute("users", userService.findAll());
        addLog("user with name="+auth.getAttribute("name")+" visit admin page");
        return "admin";
    }
    @RequestMapping(value={"", "/", "/book/showbooks"}, method= RequestMethod.GET)
    public String showbooks(@AuthenticationPrincipal OAuth2User auth, Model model){
        model.addAttribute("books",bookService.getAllBooks());
        return "books";
    }
    @GetMapping("/book/addbook/id={id}")
    public String addbook(@AuthenticationPrincipal OAuth2User auth, @PathVariable("id") int id, Model model){
        String user=userService.findByEmail(auth.getAttribute("email")).getId();
        bookService.addReportInFormular(id,user);
        bookService.updateStatusOnHands(id);
        addLog("user with id="+user+" get book with id="+id);

        return "books";
    }
    @GetMapping("/book/showformular")
    public String showformulars( Model model){
        OAuth2User userActive = ((OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(Optional.ofNullable(userActive.getAttribute("email")));
        String user=userService.findByEmail(userActive.getAttribute("email")).getId();
        System.out.println(user);

        List<BookFormular> formulars= bookService.getAllFormulars();
        List<BookFormular> formularsByUser = new java.util.ArrayList<>(List.of());
        for(BookFormular formular:formulars){
            if(Objects.equals(formular.getUser_id(), user)){
                formularsByUser.add(formular);
            }
        }
        if(formularsByUser.isEmpty()){
            model.addAttribute("msg","Пока здесь нет книг...");
        } else model.addAttribute("formulars", formularsByUser);

        return "formulars";
    }
    @GetMapping("/book/returnbook/id={id}")
    public String returnbook(@AuthenticationPrincipal OAuth2User auth, @PathVariable("id") int idFormular, Model model){
        bookService.addReturnBookInFormular(idFormular);
        addLog("user with name="+auth.getAttribute("name")+" return book. Formular with id="+idFormular);
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
}
