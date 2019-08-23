package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.OfferEntity;
import com.maxwell.myCollection.model.Offer;

@Component
public class OfferMapper {
	
	public static Offer converEntityToModel(OfferEntity entity) {
		return Offer.builder()
				.id(entity.getId())
				.itemId(entity.getItem().getId())
				.itemName(entity.getItem().getName())
				.userId(entity.getUser().getId())
				.username(entity.getUser().getUsername())
				.description(entity.getDescription())
				.status(entity.getStatus())
				.build();
	}
	
	public static List<Offer> convertEntityToModelList(List<OfferEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> Offer.builder()
				.id(entity.getId())
				.itemId(entity.getItem().getId())
				.itemName(entity.getItem().getName())
				.userId(entity.getUser().getId())
				.username(entity.getUser().getUsername())
				.description(entity.getDescription())
				.status(entity.getStatus())
				.build()).collect(Collectors.toList());
	}

}
