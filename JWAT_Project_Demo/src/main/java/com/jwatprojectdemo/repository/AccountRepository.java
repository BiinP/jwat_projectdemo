package com.jwatprojectdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwatprojectdemo.bean.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

}
