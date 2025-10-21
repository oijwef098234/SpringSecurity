package com.example.springsecurity.admin.repository;

import com.example.springsecurity.admin.entity.Admin;
import com.example.springsecurity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
