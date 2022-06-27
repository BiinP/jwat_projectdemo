package com.jwatprojectdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwatprojectdemo.bean.Category;
import com.jwatprojectdemo.repository.CategoryRepository;
import com.jwatprojectdemo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired CategoryRepository cRepo;

	@Override
	public List<Category> findAll() {
		return cRepo.findAll();
	}

	@Override
	public Category findById(String id) {
		return cRepo.findById(id).get();
	}

	@Override
	public List<Category> findByKeyword(String kw) {
		return cRepo.findByName("%"+kw+"%");
	}

	@Override
	public Category save(Category t) {
		return cRepo.save(t);
	}

	@Override
	public void deleteById(String id) {
		cRepo.deleteById(id);
	}

	@Override
	public Boolean existById(String id) {
		return cRepo.existsById(id);
	}
	
}
