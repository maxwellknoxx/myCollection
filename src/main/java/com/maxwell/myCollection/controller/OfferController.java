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

import com.maxwell.myCollection.entity.Offer;
import com.maxwell.myCollection.model.OfferDTO;
import com.maxwell.myCollection.service.impl.OfferServiceImpl;

@RestController
@CrossOrigin("*")
public class OfferController {

	@Autowired
	private OfferServiceImpl service;

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/offer/offers/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		return new ResponseEntity<OfferDTO>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PostMapping(path = "/api/v1/offer/offers")
	public ResponseEntity<?> insert(@Valid @RequestBody Offer request) {
		OfferDTO offer = service.addOffer(request);
		if (offer == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<OfferDTO>(offer, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@PutMapping(path = "/api/v1/offer/offers")
	public ResponseEntity<?> update(@Valid @RequestBody Offer request) {
		OfferDTO offer = service.updateOffer(request);
		if (offer == null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		return new ResponseEntity<OfferDTO>(offer, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@DeleteMapping(path = "/api/v1/offer/offers/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return new ResponseEntity<Boolean>(service.removeOffer(id), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	//// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/v1/offer/offers")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<List<OfferDTO>>(service.findAll(), HttpStatus.OK);
	}

}
