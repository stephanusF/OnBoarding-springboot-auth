package com.ecomindo.onboarding.testingauth.dao;

import java.util.Optional;

import com.ecomindo.onboarding.testingauth.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserDao extends JpaRepository<User, Integer> { 
    public Optional<User> findByUsername(String username);
}

