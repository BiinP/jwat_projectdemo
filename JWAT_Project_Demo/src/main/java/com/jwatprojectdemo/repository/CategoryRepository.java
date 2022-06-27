package com.jwatprojectdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwatprojectdemo.bean.Brand;
import com.jwatprojectdemo.bean.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
	@Query(value = "SELECT o FROM Category o WHERE o.name LIKE ?1 OR o.id LIKE ?1")
	public List<Category> findByName(String kw);
}
