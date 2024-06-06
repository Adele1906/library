package com.example.gateway_service.testauth.tst;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="library_books", schema = "public")
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
    @Column(name = "on_hands")
    String on_hands;


}
