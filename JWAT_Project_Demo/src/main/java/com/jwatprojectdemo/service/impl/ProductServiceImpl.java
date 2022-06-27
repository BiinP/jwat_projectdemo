package com.jwatprojectdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwatprojectdemo.bean.Product;
import com.jwatprojectdemo.repository.ProductRepository;
import com.jwatprojectdemo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired ProductRepository pRepo;
	@Override
	public List<Product> findAll() {
		return pRepo.findAll();
	}

	@Override
	public Product findById(Long id) {
		return pRepo.findById(id).get();
	}

	@Override
	public List<Product> findByKeyword(String kw) {
		return pRepo.findByKeyword("%"+kw+"%");
	}

	@Override
	public Product save(Product t) {
		return pRepo.save(t);
	}

	@Override
	public void deleteById(Long id) {
		pRepo.deleteById(id);
	}

	@Override
	public Boolean existById(Long id) {
		return pRepo.existsById(id);
	}

}
