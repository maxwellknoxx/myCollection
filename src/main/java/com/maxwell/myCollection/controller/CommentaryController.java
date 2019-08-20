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
import com.maxwell.myCollection.exception.ResourceNotFoundException;
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
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/commentary/commentaries/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) throws ResourceNotFoundException {

		Commentary commentary = service.findById(id);

		return new ResponseEntity<Commentary>(commentary, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/commentary/commentariesByItem/{id}")
	public ResponseEntity<?> getCommentariesByItem(@PathVariable("id") Long id) throws ResourceNotFoundException {
		List<Commentary> list;

		list = service.findByItemId(id);

		return new ResponseEntity<List<Commentary>>(list, HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/commentary/commentaries")
	public ResponseEntity<?> insert(@Valid @RequestBody CommentaryEntity request, BindingResult result)
			throws ResourceNotFoundException {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);
		if (errorMap != null)
			return errorMap;

		Commentary commentary = service.addCommentary(request);

		return new ResponseEntity<Commentary>(commentary, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/commentary/commentaries")
	public ResponseEntity<?> update(@Valid @RequestBody CommentaryEntity request) throws ResourceNotFoundException {
		Commentary commentary = service.findById(request.getId());
		
		System.out.println(commentary.toString());

		return new ResponseEntity<Commentary>(commentary, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/commentary/commentaries/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
		service.removeCommentary(id);

		return new ResponseEntity<String>("Commentary has been deleted", HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/commentary/allcommentaries")
	public ResponseEntity<?> findAll() throws ResourceNotFoundException {
		List<Commentary> list = service.findAll();

		return new ResponseEntity<List<Commentary>>(list, HttpStatus.OK);
	}

}
