package com.jwatprojectdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwatprojectdemo.bean.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("SELECT o FROM Product o WHERE name LIKE ?1")
	public List<Product> findByKeyword(String kw);

}
