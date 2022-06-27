package com.jwatprojectdemo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwatprojectdemo.bean.Brand;
import com.jwatprojectdemo.service.BrandService;

@RestController
@RequestMapping("/api/brands")
public class BrandAPI {
	@Autowired BrandService bService;
	
	@GetMapping("")
	public List<Brand> listBrand(){
		return bService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Brand> detailBrand(@PathVariable("id") String id) {
		if(!bService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bService.findById(id));
	}
	
	@PostMapping("")
	public ResponseEntity<Brand> save(@RequestBody Brand Brand){
		if(bService.existById(Brand.getId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(bService.save(Brand));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Brand> update(@PathVariable("id") String id, @RequestBody Brand Brand){
		if(!bService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bService.save(Brand));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		if(!bService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		bService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search")
	public List<Brand> search(@RequestParam("kw") Optional<String> kw){
		String keyword = kw.orElse(null);		
		if(keyword != null) {
			return bService.findByKeyword(keyword);
		}else {
			return this.listBrand();
		}
	}
}
