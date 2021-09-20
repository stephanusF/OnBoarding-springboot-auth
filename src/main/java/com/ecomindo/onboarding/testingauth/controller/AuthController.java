package com.ecomindo.onboarding.testingauth.controller;

import com.ecomindo.onboarding.testingauth.dto.CustomUserDTO;
import com.ecomindo.onboarding.testingauth.dto.LoginDTO;
import com.ecomindo.onboarding.testingauth.dto.ResultMsgDTO;
import com.ecomindo.onboarding.testingauth.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
	UserDetailsService userDetailsService;

    @Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	AuthenticationManager authManager;
	
    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody LoginDTO body) {
    	try {
            // ResultMsgDTO res = new ResultMsgDTO();

            Authentication authenticate = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword()));

            CustomUserDTO user = (CustomUserDTO) authenticate.getPrincipal();
            user.setToken(jwtUtil.generateAccessToken(user));
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
