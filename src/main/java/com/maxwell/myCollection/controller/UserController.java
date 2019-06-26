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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.UserEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.response.ResponseUtils;
import com.maxwell.myCollection.service.impl.UserServiceImpl;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserServiceImpl service;

	private final static Logger LOGGER = Logger.getLogger(CategoryController.class.getName());

	ResponseUtils responseUtils = new ResponseUtils();

	/**
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/api/users/allUsers")
	public ResponseEntity<Response<UserEntity>> findAll() throws ResourceNotFoundException {
		Response<UserEntity> response = new Response<>();
		List<UserEntity> list;

		try {
			list = service.findAll();
			response.setListData(list);
			response = responseUtils.setMessage(response, "Resources found", true);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Something went wrong { GET /api/users/allUsers } ");
			throw new ResourceNotFoundException("Resource not found");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/users/allUsers } completed ");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/users/user/{id}")
	public ResponseEntity<Response<UserEntity>> getUser(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<UserEntity> response = new Response<>();

		try {
			response.setData(service.findById(id).orElseThrow());
			response = responseUtils.setMessage(response, "Resource found", true);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Something went wrong { GET /api/users/user/{id} } ");
			throw new ResourceNotFoundException("Resource not found");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/users/user/{id} } completed ");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/users/user/{id}")
	public ResponseEntity<Response<UserEntity>> updateUser(@Valid @RequestBody @PathVariable("id") Long id, UserEntity request) {
		Response<UserEntity> response = new Response<>();
		UserEntity responseEntity;
		
		try {
			responseEntity = service.findById(id).orElse(null);
			if(responseEntity != null) {
				service.updateUser(request);
				response = responseUtils.setMessage(response, "Resource updated", true);
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Something went wrong { PUT /api/users/user/{id} } ");
			throw new ResourceNotFoundException("Resource not found");
		} finally {
			LOGGER.log(Level.INFO, "Operation { PUT /api/users/user/{id} } completed ");
		}
		
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @return
	 */
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/users/user/{id}")
	public ResponseEntity<Response<UserEntity>> removeUser(@Valid @PathVariable("id") Long id) {
		Response<UserEntity> response = new Response<>();

		try {
			service.removeUser(id);
			response = responseUtils.setMessage(response, "Resource deleted", true);
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "Something went wrong { DELETE /api/users/user/{id} } ");
			throw new ResourceNotFoundException("Resource not found");
		} finally {
			LOGGER.log(Level.INFO, "Operation { put /api/users/user/{id} } completed ");
		}
		
		return ResponseEntity.ok(response);
	}

}
