package com.jwatprojectdemo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwatprojectdemo.bean.Account;
import com.jwatprojectdemo.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountAPI {
	@Autowired AccountService aService;
	
	@GetMapping("")
	public List<Account> account() {
		return aService.findAll();
	}
	@GetMapping("/{id}")
	public Account account(@PathVariable("id") String id) {
		return aService.findById(id);
	}
}
