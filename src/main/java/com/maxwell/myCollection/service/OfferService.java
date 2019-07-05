package com.maxwell.myCollection.service;

import java.util.List;
import java.util.Optional;

import com.maxwell.myCollection.entity.OfferEntity;

public interface OfferService {

	List<OfferEntity> findAll();

	Optional<OfferEntity> findById(Long id);

	OfferEntity addOffer(OfferEntity offer);

	OfferEntity updateOffer(OfferEntity offer);

	void removeOffer(Long id);

}