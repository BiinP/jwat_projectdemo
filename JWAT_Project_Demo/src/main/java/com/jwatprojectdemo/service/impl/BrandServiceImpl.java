package com.jwatprojectdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwatprojectdemo.bean.Brand;
import com.jwatprojectdemo.repository.BrandRepository;
import com.jwatprojectdemo.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	@Autowired BrandRepository bRepo;
	
	@Override
	public List<Brand> findAll() {
		return bRepo.findAll();
	}

	@Override
	public Brand findById(String id) {
		return bRepo.findById(id).get();
	}

	@Override
	public List<Brand> findByKeyword(String kw) {
		return bRepo.findByName("%"+kw+"%");
	}

	@Override
	public Brand save(Brand brand) {
		return bRepo.save(brand);
	}

	@Override
	public void deleteById(String id) {
		bRepo.deleteById(id);
	}
	
	@Override
	public Boolean existById(String id) {
		return bRepo.existsById(id);
	}
}
