package com.maxwell.myCollection.utils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.maxwell.myCollection.entity.Role;
import com.maxwell.myCollection.entity.User;
import com.maxwell.myCollection.model.UserModelDTO;

@Component
public class UserMapper {

	public static UserModelDTO getDTO(User entity) {
		return UserModelDTO.builder().id(entity.getId()).name(entity.getName()).user(entity.getUsername())
				.email(entity.getEmail()).memberSince(entity.getMemberSince()).location(entity.getLocation())
				.roles(entity.getRoles())
				.role(getRole(entity.getRoles()))
				.numberTrades(entity.getNumberTrades())
				.build();
	}

	public static List<UserModelDTO> getListDTO(List<User> entities) {
		return entities.stream().filter(Objects::nonNull)
				.map(entity -> UserModelDTO.builder().id(entity.getId()).name(entity.getName()).user(entity.getUsername())
						.email(entity.getEmail()).memberSince(entity.getMemberSince()).location(entity.getLocation())
						.roles(entity.getRoles())
						.role(getRole(entity.getRoles()))
						.numberTrades(entity.getNumberTrades()).build())
				.collect(Collectors.toList());
	}

	public static String getRole(Set<Role> roles) {
		for (Role role : roles) {
			return role.getName().toString();
		}
		return "";
	}

}
