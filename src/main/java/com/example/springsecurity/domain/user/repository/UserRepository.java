package com.example.springsecurity.domain.user.repository;

import com.example.springsecurity.domain.user.entity.User;
import com.example.springsecurity.domain.user.entity.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findAllByRole(Roles roles);
    Optional<User> findByEmail(String email);
}
