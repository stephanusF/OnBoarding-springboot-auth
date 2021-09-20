package com.ecomindo.onboarding.testingauth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ecomindo.onboarding.testingauth.dao.UserDao;
import com.ecomindo.onboarding.testingauth.dto.CustomUserDTO;
import com.ecomindo.onboarding.testingauth.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("userDetailService")
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) {
		try {
			Optional<User> user = userDao.findByUsername(username);
			if (!user.isPresent()) {
				throw new UsernameNotFoundException("User " + username + " cannot be found");
			}

            List<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority(user.get().getRole());
            authorities.add(authority);

			UserDetails result = new CustomUserDTO(user.get().getUsername(), user.get().getPassword(), authorities,
					user.get().getFullname());

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}