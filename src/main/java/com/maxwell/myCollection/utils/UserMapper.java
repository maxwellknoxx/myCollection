package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.RoleEntity;
import com.maxwell.myCollection.entity.UserEntity;
import com.maxwell.myCollection.model.UserModel;

@Component
public class UserMapper {

	public static UserModel entityToModel(UserEntity entity) {
		return UserModel.builder().id(entity.getId()).name(entity.getName()).user(entity.getUsername())
				.email(entity.getEmail()).memberSince(entity.getMemberSince()).location(entity.getLocation())
				.roles(entity.getRoles())
				.role(getRole(entity.getRoles()))
				.numberTrades(entity.getNumberTrades())
				.build();
	}

	public static List<UserModel> entityToModelList(List<UserEntity> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> UserModel.builder().id(entity.getId()).name(entity.getName()).user(entity.getUsername())
						.email(entity.getEmail()).memberSince(entity.getMemberSince()).location(entity.getLocation())
						.roles(entity.getRoles())
						.role(getRole(entity.getRoles()))
						.numberTrades(entity.getNumberTrades()).build())
				.collect(Collectors.toList());
	}

	public static String getRole(Set<RoleEntity> roles) {
		for (RoleEntity role : roles) {
			return role.getName().toString();
		}
		return "";
	}

}
