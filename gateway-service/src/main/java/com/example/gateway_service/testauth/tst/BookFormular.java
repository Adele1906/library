package com.example.gateway_service.testauth.tst;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="library_book_formular", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BookFormular {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name="book_code")
    int book_code;
    @Column(name="user_id")
    String user_id;
    @Column(name="date_begin")
    Date date_begin;
    @Column(name="date_end")
    Date date_end;
    @Column(name="return_status")
    int return_status;

}
