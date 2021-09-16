package com.adesso.deneysiz.admin.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, name = "user_name", nullable = false)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
