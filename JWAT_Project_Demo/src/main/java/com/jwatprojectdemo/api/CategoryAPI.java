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

import com.jwatprojectdemo.bean.Category;
import com.jwatprojectdemo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryAPI {
	@Autowired CategoryService cService;
	
	@GetMapping("")
	public List<Category> listCategory(){
		return cService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> detailCategory(@PathVariable("id") String id) {
		if(!cService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cService.findById(id));
	}
	
	@PostMapping("")
	public ResponseEntity<Category> save(@RequestBody Category category){
		if(cService.existById(category.getId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(cService.save(category));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody Category category){
		if(!cService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cService.save(category));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String id){
		if(!cService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		cService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/search")
	public List<Category> search(@RequestParam("kw") Optional<String> kw){
		String keyword = kw.orElse(null);		
		if(keyword != null) {
			return cService.findByKeyword(keyword);
		}else {
			return this.listCategory();
		}
	}
}
