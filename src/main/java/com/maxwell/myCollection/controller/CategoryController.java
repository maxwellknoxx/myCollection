package com.maxwell.myCollection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.Category;
import com.maxwell.myCollection.model.CategoryDTO;
import com.maxwell.myCollection.service.impl.CategoryServiceImpl;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl service;

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/category/categories/{id}")
	public ResponseEntity<?> getCategory(@PathVariable("id") Long id) {
		return new ResponseEntity<CategoryDTO>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/category/categories")
	public ResponseEntity<?> addCategory(@Valid @RequestBody Category request) {
		CategoryDTO category = service.addCategory(request);
		if (category == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/category/categories")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody Category request) {
		CategoryDTO category = service.updateCategory(request);
		if (category == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/category/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeCategory(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/category/categories")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<CategoryDTO>>(service.findAll(), HttpStatus.OK);
	}

}
