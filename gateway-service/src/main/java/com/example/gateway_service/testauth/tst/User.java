package com.example.gateway_service.testauth.tst;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name="user_entity",schema = "public")
@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@ToString
public class User {
    @Id
    @Column(name="id")
private String id;
    @Column(name="first_name")
private String first_name;
    @Column(name="last_name")
private String last_name;
    @Column(name="email")
private String email;
    @Column(name="username")
private String username;
}
