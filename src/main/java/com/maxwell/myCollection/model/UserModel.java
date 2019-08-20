package com.maxwell.myCollection.model;

import java.util.Set;

import com.maxwell.myCollection.entity.RoleEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class UserModel {

	private Long id;
	private String name;
	private String user;
	private Long numberTrades;
	private String email;
	private String memberSince;
	private String location;
	private Set<RoleEntity> roles;

}
