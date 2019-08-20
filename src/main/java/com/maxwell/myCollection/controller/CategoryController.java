package com.maxwell.myCollection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		Category category = service.findById(id);
		if (category == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Category>(category, HttpStatus.OK);
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
		if (errorMap != null) {
			return errorMap;
		}

		Category category = service.addCategory(request);
		if (category == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

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
	@PutMapping(path = "/api/category/categories")
	public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryEntity request)
			throws ResourceNotFoundException {

		Category category = service.updateCategory(request);
		if (category == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Category>(category, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/api/category/categories/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) throws ResourceNotFoundException {
		if (service.removeCategory(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/category/categories")
	public ResponseEntity<?> findAll() throws ResourceNotFoundException {

		List<Category> list = service.findAll();
		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}

}
