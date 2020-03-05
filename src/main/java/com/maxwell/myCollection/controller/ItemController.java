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

import com.maxwell.myCollection.entity.Item;
import com.maxwell.myCollection.model.ItemDTO;
import com.maxwell.myCollection.service.impl.ItemServiceImpl;
import com.maxwell.myCollection.utils.DateUtils;

@RestController
@CrossOrigin(origins = "*")
public class ItemController {

	@Autowired
	private ItemServiceImpl service;

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/item/items/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		return new ResponseEntity<ItemDTO>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/item/items")
	public ResponseEntity<?> insert(@Valid @RequestBody Item request) {
		request.setPublishDate(DateUtils.getAtualDate());
		ItemDTO item = service.addItem(request);
		if (item == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<ItemDTO>(item, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/item/items")
	public ResponseEntity<?> update(@Valid @RequestBody Item request) {
		ItemDTO item = service.updateItem(request);
		if (item == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<ItemDTO>(item, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/item/items/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeItem(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/item/items")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<ItemDTO>>(service.findAll(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/item/itemsByCategory/{id}")
	public ResponseEntity<?> findAllItemsByCategory(@PathVariable("id") Long id) {
		return new ResponseEntity<List<ItemDTO>>(service.findByCategoryId(id), HttpStatus.OK);
	}

}
