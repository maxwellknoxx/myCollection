package com.maxwell.myCollection.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CategoryDTO {

	private Long id;
	private String name;
	private String description;

}
