package com.example.log_audit_server;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class LogAuditController {

    private static final String LOG_FILE_PATH = "logs.txt";
    private static final String AUDIT_FILE_PATH = "audit.txt";

    @PostMapping("/addLog")
    public ResponseEntity<String> addLogEvent(@RequestBody String log) {
        writeToFile(LOG_FILE_PATH, log);
        return new ResponseEntity<>("Log event saved successfully", HttpStatus.OK);
    }

    @PostMapping("/addAudit")
    public ResponseEntity<String> addAuditEvent(@RequestBody String audit) {
        writeToFile(AUDIT_FILE_PATH, audit);
        return new ResponseEntity<>("Audit event saved successfully", HttpStatus.OK);
    }

    private void writeToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(LocalDateTime.now() + ": " + content);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace(); // Consider logging this exception properly
        }
    }
}
