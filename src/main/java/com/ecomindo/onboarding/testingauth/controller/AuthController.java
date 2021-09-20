package com.ecomindo.onboarding.testingauth.controller;

import com.ecomindo.onboarding.testingauth.dto.LoginDTO;
import com.ecomindo.onboarding.testingauth.dto.ResultMsgDTO;
import com.ecomindo.onboarding.testingauth.model.User;
import com.ecomindo.onboarding.testingauth.service.UserService;
import com.ecomindo.onboarding.testingauth.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetailsService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    // @Autowired
	// UserDetailsService userDetailsService;
	
    @Autowired
    UserService userService;

    @Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	AuthenticationManager authManager;
	
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody LoginDTO body) {
    	try {
            ResultMsgDTO res = new ResultMsgDTO();

            User userData = userService.getUserByUsernameAndPassword(body);
            if(userData == null){
                res.setMessage("Username or password salah");
            }

            String token = jwtUtil.generateAccessToken(userData);
            res.setMessage("Login Success");
            res.setToken(token);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
