package com.ecomindo.onboarding.testingauth.service;

import com.ecomindo.onboarding.testingauth.dto.LoginDTO;
import com.ecomindo.onboarding.testingauth.model.User;

public interface UserService {
    public User getUserByUsernameAndPassword(LoginDTO data);
}
