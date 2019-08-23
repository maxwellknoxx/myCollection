package com.maxwell.myCollection.service;

import java.util.List;

import com.maxwell.myCollection.entity.OfferEntity;
import com.maxwell.myCollection.model.Offer;

public interface OfferService {

	List<Offer> findAll();

	Offer findById(Long id);

	Offer addOffer(OfferEntity offer);

	Offer updateOffer(OfferEntity offer);

	Boolean removeOffer(Long id);

}