package com.company.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {
	
	@Autowired
	private ICategoryService service;
	
	
	/*
	 *Get alll the categories
	 * @return
	 * */
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest> searchCategories(){
		ResponseEntity<CategoryResponseRest> response = service.search();
		return response;
	}
	
	
	/*
	 *Get all by id category
	 * @return
	 * */
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> searchCategoyById(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.searchById(id);
		return response;
	}
	
	/*
	 *Save categories
	 *@params Category
	 * @return
	 * */
	@PostMapping("/categories")
	public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category){
		ResponseEntity<CategoryResponseRest> response = service.save(category);
		return response;
	}
	
	
	/*
	 *UPDATE categories
	 *Id category
	 *@params Category
	 * @return
	 * */
	@PutMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> update(@RequestBody Category category, @PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.update(category, id);
		return response;
	}
	
	
	/*
	 * DELETE categories BY ID
	 * @params id
	 * @return
	 * */
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest> update(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest> response = service.deleteById(id);
		return response;
	}
}
