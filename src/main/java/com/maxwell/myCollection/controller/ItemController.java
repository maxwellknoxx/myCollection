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

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.service.impl.ItemServiceImpl;
import com.maxwell.myCollection.utils.DateUtils;
import com.maxwell.myCollection.utils.ResponseUtils;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

	@Autowired
	private ItemServiceImpl service;

	private final static Logger LOGGER = Logger.getLogger(ItemController.class.getName());

	ResponseUtils responseUtils = new ResponseUtils();

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/item/items/{id}")
	public ResponseEntity<Response<ItemEntity>> get(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<ItemEntity> response = new Response<>();
		ItemEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				response.setData(entityFromDB);
				response = responseUtils.setMessage(response, "Resource found", true);
			} else {
				throw new ResourceNotFoundException("Item not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "Something went wrong: { GET /api/item/items/{id} } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong getting the item ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/items/item } completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/item/items")
	public ResponseEntity<Response<ItemEntity>> insert(@Valid @RequestBody ItemEntity request)
			throws ResourceNotFoundException {
		Response<ItemEntity> response = new Response<>();

		try {
			request.setPublishDate(DateUtils.getAtualDate());
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

	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/item/items/{id}")
	public ResponseEntity<Response<ItemEntity>> update(@Valid @RequestBody @PathVariable("id") Long id,
			ItemEntity request) throws ResourceNotFoundException {
		Response<ItemEntity> response = new Response<>();
		ItemEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				service.updateItem(request);
				response.setData(request);
				response = responseUtils.setMessage(response, "Item updated", true);
			} else {
				throw new ResourceNotFoundException("Resource not found");
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { PUT /api/item/items/{id} } ");
			throw new ResourceNotFoundException("Something went wrong updating item " + request.getName());
		} finally {
			LOGGER.log(Level.INFO, "Operation { PUT /api/item/items/{id} }  completed");
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
	@DeleteMapping(path = "/api/item/items/{id}")
	public ResponseEntity<Response<ItemEntity>> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<ItemEntity> response = new Response<>();

		try {
			service.removeItem(id);
			response = responseUtils.setMessage(response, "Item deleted", true);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { DELETE /api/item/items/{id} } ");
			throw new ResourceNotFoundException("Something went wrong deleting item");
		} finally {
			LOGGER.log(Level.INFO, "Operation { DELETE /api/item/items/{id} } completed");
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/item/allItems")
	public ResponseEntity<Response<ItemEntity>> findAll() throws ResourceNotFoundException {
		Response<ItemEntity> response = new Response<>();
		List<ItemEntity> list;

		try {
			list = service.findAll();
			if (!list.isEmpty()) {
				response.setListData(list);
				response = responseUtils.setMessage(response, "Resources found", true);
			} else {
				response = responseUtils.setMessage(response, "Resources not found", false);
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { GET /api/item/allItems } ");
			throw new ResourceNotFoundException("Something went wrong loading all items");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/item/allItems } completed");
		}

		return ResponseEntity.ok(response);
	}
	
	@GetMapping(path = "/api/item/allItemsByCategory/{id}")
	public ResponseEntity<Response<ItemEntity>> findAllItemsByCategory(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<ItemEntity> response = new Response<>();
		List<ItemEntity> list;

		try {
			list = service.findByCategoryId(id);
			if (!list.isEmpty()) {
				response.setListData(list);
				response = responseUtils.setMessage(response, "Resources found", true);
			} else {
				response = responseUtils.setMessage(response, "Resources not found", false);
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { GET /api/item/allItemsByCategory/{id} } ");
			throw new ResourceNotFoundException("Something went wrong loading all items");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/item/allItemsByCategory/{id} } completed");
		}

		return ResponseEntity.ok(response);
	}

}
