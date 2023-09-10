package com.knoxx.mycollection.utils;

import com.knoxx.mycollection.entity.Item;
import com.knoxx.mycollection.model.ItemDto;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper extends Converter<Item, ItemDto> {

    ItemDto toDto(Item item);

    Item toEntity(ItemDto itemDTO);

    default List<ItemDto> toDtoList(List<Item> itemsList) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        itemsList.forEach(item -> itemDtoList.add(toDto(item)));
        return itemDtoList;
    }

    default List<Item> toItemList(List<ItemDto> itemDtoList) {
        List<Item> itemsList = new ArrayList<>();

        itemDtoList.forEach(item -> itemsList.add(toEntity(item)));
        return itemsList;
    }

}
