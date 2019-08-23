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

import com.maxwell.myCollection.entity.UserEntity;
import com.maxwell.myCollection.model.UserModel;
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
	@GetMapping(path = "/api/v1/users/users")
	public ResponseEntity<?> findAll()  {
		List<UserModel> list;

		list = service.findAll();
		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<List<UserModel>>(list, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1//user/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

		UserModel model = service.findById(id);
		if (model == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<UserModel>(model, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/user/users")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserEntity request) {
		UserEntity entityFromDB;
		UserModel entityFromDBModel = null;

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

		return new ResponseEntity<UserModel>(entityFromDBModel, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/user/users/{id}")
	public ResponseEntity<?> removeUser(@Valid @PathVariable("id") Long id) {

		if (service.removeUser(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/user/information")
	public ResponseEntity<?> checkRecoverInformation(@Valid @RequestBody UserEntity request) {
		UserEntity userFromDB;

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
