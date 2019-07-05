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

import com.maxwell.myCollection.entity.OfferEntity;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.response.Response;
import com.maxwell.myCollection.response.ResponseUtils;
import com.maxwell.myCollection.service.impl.OfferServiceImpl;

@RestController
@CrossOrigin("*")
public class OfferController {

	@Autowired
	private OfferServiceImpl service;
	
	private final static Logger LOGGER = Logger.getLogger(OfferController.class.getName());

	ResponseUtils responseUtils = new ResponseUtils();
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/offer/offers/{id}")
	public ResponseEntity<Response<OfferEntity>> get(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<OfferEntity> response = new Response<>();
		OfferEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				response.setData(entityFromDB);
				response = responseUtils.setMessage(response, "Resource found", true);
			} else {
				throw new ResourceNotFoundException("Offer not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "Something went wrong: { GET /api/offer/offers/{id} } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong getting the Offer ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/offer/offers } completed");
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
	@PostMapping(path = "/api/offer/offers")
	public ResponseEntity<Response<OfferEntity>> insert(@Valid @RequestBody OfferEntity request)
			throws ResourceNotFoundException {
		Response<OfferEntity> response = new Response<>();

		try {
			request = service.addOffer(request);
			response.setData(request);
			response = responseUtils.setMessage(response, "Offer has been added", true);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.log(Level.WARNING, "Something went wrong: { POST /api/offer/offers } " + e.getMessage());
			throw new ResourceNotFoundException("Something went wrong adding the Offer ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { POST /api/offer/offers } completed");
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
	@PutMapping(path = "/api/offer/offers/{id}")
	public ResponseEntity<Response<OfferEntity>> update(@Valid @RequestBody @PathVariable("id") Long id,
			OfferEntity request) throws ResourceNotFoundException {
		Response<OfferEntity> response = new Response<>();
		OfferEntity entityFromDB;

		try {
			entityFromDB = service.findById(id).orElseThrow();
			if (entityFromDB != null) {
				service.updateOffer(request);
				response.setData(request);
				response = responseUtils.setMessage(response, "Offer updated", true);
			} else {
				throw new ResourceNotFoundException("Resource not found");
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { PUT /api/offer/offers/{id} } ");
			throw new ResourceNotFoundException("Something went wrong updating Offer ");
		} finally {
			LOGGER.log(Level.INFO, "Operation { PUT /api/offer/offers/{id} }  completed");
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
	@DeleteMapping(path = "/api/offer/offers/{id}")
	public ResponseEntity<Response<OfferEntity>> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
		Response<OfferEntity> response = new Response<>();
		
		try {
			service.removeOffer(id);
			response = responseUtils.setMessage(response, "Offer deleted", true);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { DELETE /api/offer/offers/{id} } ");
			throw new ResourceNotFoundException("Something went wrong deleting Offer");
		} finally {
			LOGGER.log(Level.INFO, "Operation { DELETE /api/offer/offers/{id} } completed");
		}

		
		return ResponseEntity.ok(response);
	}
	
	/**
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	////@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping(path = "/api/offer/allOffers")
	public ResponseEntity<Response<OfferEntity>> findAll() throws ResourceNotFoundException {
		Response<OfferEntity> response = new Response<>();
		List<OfferEntity> list;
		
		try {
			list = service.findAll();
			if(!list.isEmpty()) {
				response.setListData(list);
				response = responseUtils.setMessage(response, "Resources found", true);
			} else {
				response = responseUtils.setMessage(response, "Resources not found", false);
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Something went wrong { GET //api/offer/allOffers } ");
			throw new ResourceNotFoundException("Something went wrong loading all Offers");
		} finally {
			LOGGER.log(Level.INFO, "Operation { GET /api/offer/allOffers } completed");
		}
		
		return ResponseEntity.ok(response);
	}
	
}
