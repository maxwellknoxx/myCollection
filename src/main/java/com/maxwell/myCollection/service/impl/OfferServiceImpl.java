package com.maxwell.myCollection.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.OfferEntity;
import com.maxwell.myCollection.repository.OfferRepository;
import com.maxwell.myCollection.service.OfferService;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository repository;

	@Override
	public List<OfferEntity> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<OfferEntity> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public OfferEntity addOffer(OfferEntity offer) {
		return repository.save(offer);
	}

	@Override
	public OfferEntity updateOffer(OfferEntity offer) {
		return repository.save(offer);
	}

	@Override
	public void removeOffer(Long id) {
		repository.deleteById(id);
	}

}