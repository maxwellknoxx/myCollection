package com.maxwell.myCollection.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxwell.myCollection.entity.OfferEntity;
import com.maxwell.myCollection.model.Offer;
import com.maxwell.myCollection.repository.OfferRepository;
import com.maxwell.myCollection.service.OfferService;
import com.maxwell.myCollection.utils.OfferMapper;

@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	private OfferRepository repository;

	@Override
	public List<Offer> findAll() {
		List<OfferEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return OfferMapper.convertEntityToModelList(list);
	}

	@Override
	public Offer findById(Long id) {
		OfferEntity entity = repository.findById(id).orElse(null);
		if (entity == null) {
			return null;
		}
		return OfferMapper.converEntityToModel(entity);

	}

	@Override
	public Offer addOffer(OfferEntity offer) {
		OfferEntity entity = repository.save(offer);
		if (entity == null) {
			return null;
		}
		return OfferMapper.converEntityToModel(entity);
	}

	@Override
	public Offer updateOffer(OfferEntity offer) {
		OfferEntity entity = repository.save(offer);
		if (entity == null) {
			return null;
		}
		return OfferMapper.converEntityToModel(entity);
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