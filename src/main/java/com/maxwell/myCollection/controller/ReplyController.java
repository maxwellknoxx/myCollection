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

import com.maxwell.myCollection.entity.ReplyEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.service.ReplyService;
import com.maxwell.myCollection.utils.ResponseUtils;

@RestController
@CrossOrigin("*")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	private final static Logger LOGGER = Logger.getLogger(ReplyController.class.getName());

	ResponseUtils responseUtils = new ResponseUtils();

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/reply/replies/{id}")
	public ResponseEntity<Response<ReplyEntity>> get(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<ReplyEntity> response = new Response<>();
		ReplyEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				response.setData(entityFromDB);
				response = responseUtils.setMessage(response, "Resource found", true);
			} else {
				throw new ResourceNotFoundException("Reply not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "Something went wrong: { GET /api/reply/replies/{id} } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong getting the Reply ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/reply/replies } completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/reply/replies")
	public ResponseEntity<Response<ReplyEntity>> insert(@Valid @RequestBody ReplyEntity request)
			throws ResourceNotFoundException {
		Response<ReplyEntity> response = new Response<>();

		try {
			request = service.addReply(request);
			response.setData(request);
			response = responseUtils.setMessage(response, "Reply has been added", true);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "Something went wrong: { POST /api/reply/replies } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong adding the Reply ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { POST /api/reply/replies } completed");
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
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/reply/replies/{id}")
	public ResponseEntity<Response<ReplyEntity>> update(@Valid @RequestBody @PathVariable("id") Long id,
			ReplyEntity request) throws ResourceNotFoundException {
		Response<ReplyEntity> response = new Response<>();
		ReplyEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				service.updateReply(request);
				response.setData(request);
				response = responseUtils.setMessage(response, "Reply updated", true);
			} else {
				throw new ResourceNotFoundException("Resource not found");
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { PUT /api/reply/replies/{id} } ");
			throw new ResourceNotFoundException("Something went wrong updating Reply ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { PUT /api/reply/replies/{id} }  completed");
		}

		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/reply/replies/{id}")
	public ResponseEntity<Response<ReplyEntity>> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<ReplyEntity> response = new Response<>();
		
		try {
			service.removeReply(id);
			response = responseUtils.setMessage(response, "Reply deleted", true);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { DELETE /api/reply/replies/{id} } ");
			throw new ResourceNotFoundException("Something went wrong deleting Reply");
		} finally {
			LOGGER.log(Level.INFO, "Operation { DELETE /api/reply/replies/{id} } completed");
		}

		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	////@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/reply/allReplies")
	public ResponseEntity<Response<ReplyEntity>> findAll() throws ResourceNotFoundException {
		Response<ReplyEntity> response = new Response<>();
		List<ReplyEntity> list;
		
		try {
			list = service.findAll();
			if(!list.isEmpty()) {
				response.setListData(list);
				response = responseUtils.setMessage(response, "Resources found", true);
			} else {
				response = responseUtils.setMessage(response, "Resources not found", false);
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { GET /api/reply/allReplies } ");
			throw new ResourceNotFoundException("Something went wrong loading all Replys");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/reply/allReplies } completed");
		}
		
		return ResponseEntity.ok(response);
	}


}
