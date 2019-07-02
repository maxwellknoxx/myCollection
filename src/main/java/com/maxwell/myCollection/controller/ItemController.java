package com.maxwell.myCollection.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.response.ResponseUtils;
import com.maxwell.myCollection.service.impl.ItemServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

	@Autowired
	private ItemServiceImpl service;

	private final static Logger LOGGER = Logger.getLogger(ItemController.class.getName());

	ResponseUtils responseUtils = new ResponseUtils();

	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/items/item")
	public ResponseEntity<Response<ItemEntity>> addItem(@Valid @RequestBody ItemEntity request)
			throws ResourceNotFoundException {
		Response<ItemEntity> response = new Response<>();
		
		try {
			request = service.addItem(request);
			response.setData(request);
			response = responseUtils.setMessage(response, "Item " + request.getName() + " has been added", true);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "Something went wrong: { POST /api/items/item } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong adding the item " + request.getName());
		} finally {
			LOGGER.log(Level.INFO, "Operation { POST /api/items/item } completed");
		}

		return ResponseEntity.ok(response);
	}

}
