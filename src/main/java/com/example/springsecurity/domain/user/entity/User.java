package com.example.springsecurity.domain.user.entity;

import com.example.springsecurity.domain.user.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;

    private boolean isEnabled;

    private boolean isLocked;

    private boolean isExpired;

    private boolean isCredentialsExpired;

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }
}