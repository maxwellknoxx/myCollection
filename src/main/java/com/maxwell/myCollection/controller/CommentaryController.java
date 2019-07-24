package com.maxwell.myCollection.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.service.impl.CommentaryServiceImpl;
import com.maxwell.myCollection.utils.ResponseUtils;

@RestController
@CrossOrigin("*")
public class CommentaryController {

	@Autowired
	private CommentaryServiceImpl service;

	private final static Logger LOGGER = Logger.getLogger(CommentaryController.class.getName());

	ResponseUtils responseUtils = new ResponseUtils();

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/commentary/commentaries/{id}")
	public ResponseEntity<Response<CommentaryEntity>> get(@PathVariable("id") Long id)
			throws ResourceNotFoundException {
		Response<CommentaryEntity> response = new Response<>();
		CommentaryEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				response.setData(entityFromDB);
				response = responseUtils.setMessage(response, "Resource found", true);
			} else {
				throw new ResourceNotFoundException("Commentary not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING,
					"Something went wrong: { GET /api/commentary/commentaries/{id} } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong getting the commentary ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/commentary/commentaries/{id} } completed");
		}

		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/commentary/commentariesByItem/{id}")
	public ResponseEntity<Response<CommentaryEntity>> getCommentariesByItem(@PathVariable("id") Long id)
			throws ResourceNotFoundException {
		Response<CommentaryEntity> response = new Response<>();
		List<CommentaryEntity> entityFromDB;

		try {
			entityFromDB = service.findByItemId(id);
			if (entityFromDB != null) {
				response.setListData(entityFromDB);
				response = responseUtils.setMessage(response, "Resource found", true);
			} else {
				throw new ResourceNotFoundException("Commentaries not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING,
					"Something went wrong: { GET /api/commentary/commentariesByItem/{id} } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong getting the commentaries ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/commentary/commentariesByItem/{id} } completed");
		}
		
		//for(CommentaryEntity c : entityFromDB) {
		//	System.out.println(c.toString());
		//}
		

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/commentary/commentaries")
	public ResponseEntity<Response<CommentaryEntity>> insert(@Valid @RequestBody CommentaryEntity request)
			throws ResourceNotFoundException {
		Response<CommentaryEntity> response = new Response<>();

		try {
			request = service.addCommentary(request);
			response.setData(request);
			response = responseUtils.setMessage(response, "The commentary has been added", true);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "Something went wrong: { POST /api/commentary/commentaries } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong adding the commentary ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { POST /api/commentary/commentaries } completed");
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
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/commentary/commentaries/{id}")
	public ResponseEntity<Response<CommentaryEntity>> update(@Valid @RequestBody @PathVariable("id") Long id,
			CommentaryEntity request) throws ResourceNotFoundException {
		Response<CommentaryEntity> response = new Response<>();
		CommentaryEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				service.updateCommentary(request);
				response.setData(request);
				response = responseUtils.setMessage(response, "Commentary updated", true);
			} else {
				throw new ResourceNotFoundException("Resource not found");
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { PUT /api/commentary/commentaries/{id} } ");
			throw new ResourceNotFoundException("Something went wrong updating commentary ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { PUT /api/commentary/commentaries/{id} }  completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/commentary/commentaries/{id}")
	public ResponseEntity<Response<CommentaryEntity>> delete(@PathVariable("id") Long id)
			throws ResourceNotFoundException {
		Response<CommentaryEntity> response = new Response<>();

		try {
			service.removeCommentary(id);
			response = responseUtils.setMessage(response, "Commentary deleted", true);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { DELETE /api/commentary/commentaries/{id} } ");
			throw new ResourceNotFoundException("Something went wrong deleting commentary");
		} finally {
			LOGGER.log(Level.INFO, "Operation { DELETE /api/commentary/commentaries/{id} } completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/commentary/allcommentaries")
	public ResponseEntity<Response<CommentaryEntity>> findAll() throws ResourceNotFoundException {
		Response<CommentaryEntity> response = new Response<>();
		List<CommentaryEntity> list;

		try {
			list = service.findAll();
			if (!list.isEmpty()) {
				response.setListData(list);
				response = responseUtils.setMessage(response, "Resources found", true);
			} else {
				response = responseUtils.setMessage(response, "Resources not found", false);
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { GET /api/commentary/allcommentaries } ");
			throw new ResourceNotFoundException("Something went wrong loading all commentaries");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/commentary/allcommentaries } completed");
		}

		return ResponseEntity.ok(response);
	}

}
