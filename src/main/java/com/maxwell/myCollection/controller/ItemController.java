package com.maxwell.myCollection.controller;

import java.util.Arrays;
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

import com.maxwell.myCollection.entity.ItemEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.Item;
import com.maxwell.myCollection.service.impl.ItemServiceImpl;
import com.maxwell.myCollection.service.impl.MapValidationErrorService;
import com.maxwell.myCollection.utils.DateUtils;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

	@Autowired
	private ItemServiceImpl service;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/item/items/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Item item = service.findById(id);
		if (item == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<Item>(item, HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/item/items")
	public ResponseEntity<?> insert(@Valid @RequestBody ItemEntity request, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidation(result);

		if (errorMap != null)
			return errorMap;

		request.setPublishDate(DateUtils.getAtualDate());
		Item item = service.addItem(request);
		if (item == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Item>(item, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/item/items")
	public ResponseEntity<?> update(@Valid @RequestBody ItemEntity request) {
		Item item = service.updateItem(request);
		if (item == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<Item>(item, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/item/items/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.removeItem(id)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/item/items")
	public ResponseEntity<?> findAll() {
		List<Item> list = service.findAll();
		if (list.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		System.out.println(list.toString());
		return new ResponseEntity<List<Item>>(list, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/item/itemsByCategory/{id}")
	public ResponseEntity<?> findAllItemsByCategory(@PathVariable("id") Long id) {
		List<Item> list = service.findByCategoryId(id);
		if (list.isEmpty()) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		return new ResponseEntity<List<Item>>(list, HttpStatus.OK);
	}

}
