package com.jwatprojectdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jwatprojectdemo.bean.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String>{
	@Query(value = "SELECT o FROM Brand o WHERE o.name LIKE ?1 OR o.id LIKE ?1")
	public List<Brand> findByName(String kw);
}
