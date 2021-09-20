package com.ecomindo.onboarding.testingauth.service;

import com.ecomindo.onboarding.testingauth.dao.UserDao;
import com.ecomindo.onboarding.testingauth.dto.LoginDTO;
import com.ecomindo.onboarding.testingauth.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    
    @Override
    public User getUserByUsernameAndPassword(LoginDTO data) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User res = userDao.findAll().stream()
            .filter(x -> x.getUsername().equals(data.getUsername()) && x.getPassword().equals(encoder.encode(data.getPassword()))).findAny().orElse(null);
        
        return res;
    }
    
}
