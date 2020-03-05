package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.Offer;
import com.maxwell.myCollection.model.OfferDTO;

public interface OfferService {

	List<OfferDTO> findAll();

	OfferDTO findById(Long id);

	OfferDTO addOffer(Offer offer);

	OfferDTO updateOffer(Offer offer);

	Boolean removeOffer(Long id);

}