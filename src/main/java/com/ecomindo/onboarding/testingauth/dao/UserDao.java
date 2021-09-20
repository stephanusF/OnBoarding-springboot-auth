package com.ecomindo.onboarding.testingauth.dao;

import com.ecomindo.onboarding.testingauth.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface UserDao extends JpaRepository<User, Integer> { 

}

