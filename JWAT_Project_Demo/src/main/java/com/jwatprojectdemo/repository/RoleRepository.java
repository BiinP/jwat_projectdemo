package com.jwatprojectdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwatprojectdemo.bean.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{

}
