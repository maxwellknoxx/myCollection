package com.knoxx.mycollection.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Builder
@Getter
@Setter
@ToString
public class CategoryDto {

	private String categoryId;
	private String name;
	private String description;

}
