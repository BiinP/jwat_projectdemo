package com.jwatprojectdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwatprojectdemo.bean.Account;
import com.jwatprojectdemo.service.AccountService;

@Service
public class UserServiceImpl implements UserDetailsService{
	@Autowired AccountService aService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			Account account = aService.findById(username);
			String password = account.getPassword();
//			String role[] = null;
			String role= account.getRole().getId();
			return User.withUsername(username).password(passwordEncoder.encode(password)).roles(role).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + " not found");
		}
	}

}
