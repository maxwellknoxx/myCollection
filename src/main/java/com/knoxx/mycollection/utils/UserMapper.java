package com.knoxx.mycollection.utils;

import com.knoxx.mycollection.entity.User;
import com.knoxx.mycollection.model.UserDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<User, UserDto> {

    UserDto toDto(User user);

    User toEntity(UserDto user);

    default List<UserDto> toDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();

        userList.forEach(user -> userDtoList.add(toDto(user)));
        return userDtoList;
    }

    default List<User> toEntityList(List<UserDto> userDtoList1) {
        List<User> entity = new ArrayList<>();

        userDtoList1.forEach(user -> entity.add(toEntity(user)));
        return entity;
    }

}
