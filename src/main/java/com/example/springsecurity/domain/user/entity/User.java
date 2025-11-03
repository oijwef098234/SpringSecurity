package com.example.springsecurity.domain.user.entity;

import com.example.springsecurity.domain.user.entity.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String username;
    private String password;

    private Roles role;

    private boolean isEnabled;

    private boolean isLocked;

    private boolean isExpired;

    private boolean isCredentialsExpired;

    public void lockAccount() {
        this.isLocked = true;
    }
}