package com.example.bookservice.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name="books")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter@Setter
public class Book {
    @Id
    @Column(name="code")
    int code;
    @Column(name="name")
    String name;
    @Column(name="description")
    String description;

}
