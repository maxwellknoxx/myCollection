package com.maxwell.myCollection.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.response.ResponseUtils;
import com.maxwell.myCollection.service.impl.CategoryServiceImpl;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class CategoryController {

	private final static Logger LOGGER = Logger.getLogger(CategoryController.class.getName());
	
	ResponseUtils responseUtils = new ResponseUtils();

	@Autowired
	private CategoryServiceImpl service;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/category/categories/{id}")
	public ResponseEntity<Response<CategoryEntity>> getCategory(@PathVariable("id") Long id)
			throws ResourceNotFoundException {
		Response<CategoryEntity> response = new Response<>();

		try {
			response.setData(service.findById(id).orElseThrow());
			response = responseUtils.setMessage(response, "Resource found", true);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong: { GET /api/category/categories/{id} } ");
			throw new ResourceNotFoundException("Category not found");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/category/categories/{id} } completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/category/categories")
	public ResponseEntity<Response<CategoryEntity>> addCategory(@Valid @RequestBody CategoryEntity request)
			throws ResourceNotFoundException {
		Response<CategoryEntity> response = new Response<>();
		CategoryEntity responseEntity;

		try {
			responseEntity = service.addCategory(request);
			response.setData(responseEntity);
			response = responseUtils.setMessage(response, "Category added", true);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { POST /api/category/categories/{id} } ");
			throw new ResourceNotFoundException("Something went wrong adding category " + request.getName());
		} finally {
			LOGGER.log(Level.INFO, "Operation { POST /api/category/categories/{id} } completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/category/categories/{id}")
	public ResponseEntity<Response<CategoryEntity>> updateCategory(@Valid @RequestBody @PathVariable("id") Long id,
			CategoryEntity request) throws ResourceNotFoundException {
		Response<CategoryEntity> response = new Response<>();
		CategoryEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				service.updateCategory(request);
				response.setData(request);
				response = responseUtils.setMessage(response, "Category updated", true);
			} else {
				throw new ResourceNotFoundException("Resource not found");
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { PUT /api/category/categories/{id} } ");
			throw new ResourceNotFoundException("Something went wrong updating category " + request.getName());
		} finally {
			LOGGER.log(Level.INFO, "Operation { PUT /api/category/categories/{id} }  completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(path = "/api/category/categories/{id}")
	public ResponseEntity<Response<CategoryEntity>> deleteCategory(@PathVariable("id") Long id)
			throws ResourceNotFoundException {
		Response<CategoryEntity> response = new Response<>();

		try {
			service.removeCategory(id);
			response = responseUtils.setMessage(response, "Category deleted", true);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { DELETE /api/category/categories/{id} } ");
			throw new ResourceNotFoundException("Something went wrong deleting category");
		} finally {
			LOGGER.log(Level.INFO, "Operation { DELETE /api/category/categories/{id} } completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/category/allCategories")
	public ResponseEntity<Response<CategoryEntity>> findAll() throws ResourceNotFoundException {
		Response<CategoryEntity> response = new Response<>();
		List<CategoryEntity> list;
		
		try {
			list = service.findAll();
			if(!list.isEmpty()) {
				response.setListData(list);
				response = responseUtils.setMessage(response, "Resources found", true);
			} else {
				response = responseUtils.setMessage(response, "Resources not found", false);
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { GET /api/category/allCategories } ");
			throw new ResourceNotFoundException("Something went wrong loading all categories");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/category/allCategories } completed");
		}
		
		return ResponseEntity.ok(response);
	}

}
