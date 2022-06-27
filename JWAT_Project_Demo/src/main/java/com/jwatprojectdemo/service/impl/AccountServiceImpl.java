package com.jwatprojectdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwatprojectdemo.bean.Account;
import com.jwatprojectdemo.repository.AccountRepository;
import com.jwatprojectdemo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired AccountRepository aRepo;
	
	@Override
	public List<Account> findAll() {
		return aRepo.findAll();
	}

	@Override
	public Account findById(String id) {
		return aRepo.findById(id).get();
	}

	@Override
	public List<Account> findByKeyword(String kw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account save(Account t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean existById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
