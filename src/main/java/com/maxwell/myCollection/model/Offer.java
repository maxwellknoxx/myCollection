package com.maxwell.myCollection.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Offer {

	
	private Long id;
	private Long itemId;
	private String itemName;
	private Long userId;
	private String username;
	private String description;
	private Boolean status;
	
}
