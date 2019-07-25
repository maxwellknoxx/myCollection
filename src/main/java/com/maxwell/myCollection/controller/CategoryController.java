package com.maxwell.myCollection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.CategoryEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.Category;
import com.maxwell.myCollection.service.impl.CategoryServiceImpl;
import com.maxwell.myCollection.service.impl.MapValidationErrorService;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/category/categories/{id}")
	public ResponseEntity<?> getCategory(@PathVariable("id") Long id) throws ResourceNotFoundException {
		return new ResponseEntity<Category>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/category/categories")
	public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryEntity request, BindingResult result)
			throws ResourceNotFoundException {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null)
			return errorMap;

		Category category = service.addCategory(request);

		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/category/categories/{id}")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody @PathVariable("id") Long id, CategoryEntity request)
			throws ResourceNotFoundException {

		Category category = service.findById(id);

		if (category == null) {
			return new ResponseEntity<String>("Something went wrong", HttpStatus.BAD_REQUEST);
		}

		category = service.updateCategory(request);

		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PreAuthorize("hasRole('ADMIN')")
	// @DeleteMapping(path = "/api/category/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) throws ResourceNotFoundException {
		service.removeCategory(id);

		return new ResponseEntity<String>("Category " + id + " has been deleted", HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/category/allCategories")
	public ResponseEntity<?> findAll() throws ResourceNotFoundException {
		List<Category> list;

		list = service.findAll();

		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}

}
