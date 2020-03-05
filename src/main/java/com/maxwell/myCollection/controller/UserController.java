package com.maxwell.myCollection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.User;
import com.maxwell.myCollection.model.UserModelDTO;
import com.maxwell.myCollection.service.impl.UserServiceImpl;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserServiceImpl service;

	@Autowired
	private PasswordEncoder encoder;

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/user/users")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<UserModelDTO>>(service.findAll(), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/user/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
		return new ResponseEntity<UserModelDTO>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/user/users")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User request) {
		User entityFromDB;
		UserModelDTO entityFromDBModel = null;

		String password = encoder.encode(request.getPassword());
		entityFromDB = service.getUser(request.getId());
		if (entityFromDB != null) {
			request = entityFromDB;
			request.setPassword(password);
			entityFromDBModel = service.updateUser(request);
			if (entityFromDBModel == null) {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}

		return new ResponseEntity<UserModelDTO>(entityFromDBModel, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/user/users/{id}")
	public ResponseEntity<?> removeUser(@Valid @PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeUser(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/user/information")
	public ResponseEntity<?> checkRecoverInformation(@Valid @RequestBody User request) {
		User userFromDB;

		userFromDB = service.getByEmail(request.getEmail());
		if (userFromDB != null) {
			if (userFromDB.getAnswer().toLowerCase().equals(request.getAnswer().toLowerCase())
					&& userFromDB.getEmail().equals(request.getEmail())) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

}
