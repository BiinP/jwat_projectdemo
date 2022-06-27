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

import com.jwatprojectdemo.bean.Product;
import com.jwatprojectdemo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {
	@Autowired ProductService pService;
	
	@GetMapping("")
	public List<Product> listProduct(){
		return pService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> detailProduct(@PathVariable("id") Long id) {
		if(!pService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pService.findById(id));
	}
	
	@PostMapping("")
	public ResponseEntity<Product> save(@RequestBody Product Product){
		if(pService.existById(Product.getId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(pService.save(Product));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product Product){
		if(!pService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pService.save(Product));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		if(!pService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		pService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search")
	public List<Product> search(@RequestParam("kw") Optional<String> kw){
		String keyword = kw.orElse(null);		
		if(keyword != null) {
			return pService.findByKeyword(keyword);
		}else {
			return this.listProduct();
		}
	}
}
