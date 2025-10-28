package com.example.rate_limiting_demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;

    @Column(name = "username", updatable = false, unique = true)
    private String username;

    @Column(name = "email", updatable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
