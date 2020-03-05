package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.Offer;
import com.maxwell.myCollection.exception.ResourceNotFoundException;
import com.maxwell.myCollection.model.OfferDTO;
import com.maxwell.myCollection.repository.OfferRepository;
import com.maxwell.myCollection.service.OfferService;
import com.maxwell.myCollection.utils.OfferMapper;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository repository;

	@Override
	public List<OfferDTO> findAll() throws ResourceNotFoundException {
		List<Offer> list = repository.findAll();
		if (list.isEmpty()) {
			throw new ResourceNotFoundException(Offer.class, "No offer found");
		}
		return OfferMapper.getListDTO(list);
	}

	@Override
	public OfferDTO findById(Long id) throws ResourceNotFoundException {
		Offer entity = repository.findById(id).orElse(null);
		if (entity == null) {
			throw new ResourceNotFoundException(Offer.class, "No offer found");
		}
		return OfferMapper.getDTO(entity);
	}

	@Override
	public OfferDTO addOffer(Offer offer) {
		Offer entity = repository.save(offer);
		if (entity == null) {
			return null;
		}
		return OfferMapper.getDTO(entity);
	}

	@Override
	public OfferDTO updateOffer(Offer offer) {
		Offer entity = repository.save(offer);
		if (entity == null) {
			return null;
		}
		return OfferMapper.getDTO(entity);
	}

	@Override
	public Boolean removeOffer(Long id) {
		try {
			repository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}