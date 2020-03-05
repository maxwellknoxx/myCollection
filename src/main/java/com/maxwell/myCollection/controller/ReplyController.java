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

import com.maxwell.myCollection.entity.Reply;
import com.maxwell.myCollection.model.ReplyDTO;
import com.maxwell.myCollection.service.ReplyService;

@RestController
@CrossOrigin("*")
public class ReplyController {

	@Autowired
	private ReplyService service;

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/reply/replies/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		return new ResponseEntity<ReplyDTO>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/reply/replies")
	public ResponseEntity<?> insert(@Valid @RequestBody Reply request) {
		ReplyDTO reply = service.addReply(request);
		if (reply == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<ReplyDTO>(reply, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/reply/replies")
	public ResponseEntity<?> update(@Valid @RequestBody Reply request) {
		ReplyDTO reply = service.updateReply(request);
		if (reply == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<ReplyDTO>(reply, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/reply/replies/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeReply(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/reply/replies")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<ReplyDTO>>(service.findAll(), HttpStatus.OK);
	}

}
