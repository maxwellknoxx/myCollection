package com.maxwell.myCollection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.CommentaryEntity;
import com.maxwell.myCollection.model.Commentary;
import com.maxwell.myCollection.service.impl.CommentaryServiceImpl;
import com.maxwell.myCollection.service.impl.MapValidationErrorService;

@RestController
@CrossOrigin("*")
public class CommentaryController {

	@Autowired
	private CommentaryServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * 
	 * @param id
	 * @return @
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/commentary/commentaries/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {

		Commentary commentary = service.findById(id);
		if (commentary == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Commentary>(commentary, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return @
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/commentary/commentariesByItem/{id}")
	public ResponseEntity<?> getCommentariesByItem(@PathVariable("id") Long id) {
		List<Commentary> list;

		list = service.findByItemId(id);
		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<List<Commentary>>(list, HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return @
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/commentary/commentaries")
	public ResponseEntity<?> insert(@Valid @RequestBody CommentaryEntity request, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null)
			return errorMap;

		Commentary commentary = service.addCommentary(request);
		if (commentary == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Commentary>(commentary, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @return @
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/commentary/commentaries")
	public ResponseEntity<?> update(@Valid @RequestBody CommentaryEntity request) {
		Commentary commentary = service.updateCommentary(request);
		if (commentary == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Commentary>(commentary, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @return @
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/commentary/commentaries/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.removeCommentary(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * 
	 * @return @
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/commentary/commentaries")
	public ResponseEntity<?> findAll() {
		List<Commentary> list = service.findAll();
		if (list == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<List<Commentary>>(list, HttpStatus.OK);
	}
	
}
