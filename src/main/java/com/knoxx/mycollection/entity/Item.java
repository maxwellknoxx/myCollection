package com.knoxx.mycollection.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("items")
public class Item {

	@Id
	private String itemId;
	private String categoryId;
	private String categoryName;
	private String userId;
	private String userName;
	private String location;
	private String itemName;
	private String itemCondition;
	private String description;
	private String photo;
	private String trade;
	private String status;
	private String publishDate;
//	private List<Offer> offers = new ArrayList<>();
//	private List<Commentary> commentaries = new ArrayList<>();

}
