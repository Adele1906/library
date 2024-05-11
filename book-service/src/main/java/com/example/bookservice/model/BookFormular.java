package com.example.bookservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="book_formular")
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
    int user_id;
    @Column(name="date_begin")
    Date date_begin;
    @Column(name="date_end")
    Date date_end;
    @Column(name="return_status")
    int return_status;

}