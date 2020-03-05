package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.Offer;
import com.maxwell.myCollection.model.OfferDTO;

@Component
public class OfferMapper {
	
	public static OfferDTO getDTO(Offer entity) {
		return OfferDTO.builder()
				.id(entity.getId())
				.itemId(entity.getItem().getId())
				.itemName(entity.getItem().getName())
				.userId(entity.getUser().getId())
				.username(entity.getUser().getUsername())
				.description(entity.getDescription())
				.status(entity.getStatus())
				.build();
	}
	
	public static List<OfferDTO> getListDTO(List<Offer> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> OfferDTO.builder()
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
