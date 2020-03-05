package com.maxwell.myCollection.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.Commentary;
import com.maxwell.myCollection.model.CommentaryDTO;
import com.maxwell.myCollection.service.impl.CommentaryServiceImpl;

@RestController
@CrossOrigin("*")
public class CommentaryController {

	@Autowired
	private CommentaryServiceImpl service;

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/commentary/commentaries/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		return new ResponseEntity<CommentaryDTO>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/commentary/commentariesByItem/{id}")
	public ResponseEntity<?> getCommentariesByItem(@PathVariable("id") Long id) {
		return new ResponseEntity<List<CommentaryDTO>>(service.findByItemId(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/commentary/commentaries")
	public ResponseEntity<?> insert(@Valid @RequestBody Commentary request) {
		CommentaryDTO commentary = service.addCommentary(request);
		if (commentary == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<CommentaryDTO>(commentary, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/commentary/commentaries")
	public ResponseEntity<?> update(@Valid @RequestBody Commentary request) {
		CommentaryDTO commentary = service.updateCommentary(request);
		if (commentary == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<CommentaryDTO>(commentary, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/commentary/commentaries/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeCommentary(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/commentary/commentaries")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<CommentaryDTO>>(service.findAll(), HttpStatus.OK);
	}

}
