package org.example.userservice.controller;

import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping("/user")
public class LkController {

    @Autowired
    UserService userService;

    @GetMapping("/lk")
    public String lk(@CookieValue(value = "iddus") String iddus, Model model){
        userService.findById(iddus).getUsername();
        model.addAttribute("user",userService.findById(iddus).getUsername());
        addLog("user with name="+userService.findById(iddus).getUsername()+" visit lk");

        return "lk";
    }
    @GetMapping("/admin")
    public String adminpage( @CookieValue(value = "iddus") String iddus, Model model){
        model.addAttribute("users", userService.findAll());
        addLog("user with name="+iddus+" visit admin page");
        return "admin";
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
