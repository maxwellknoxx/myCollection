package com.maxwell.myCollection.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Item {

	private Long id;
	private String categoryName;
	private Long categoryId;
	private String Name;
	private String username;
	private Long userId;
	private String itemCondition;
	private String description;
	private String photo;
	private String trade;
	private String status;
	private String location;
	private List<Commentary> commentaries;

}
