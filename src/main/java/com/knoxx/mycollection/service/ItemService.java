package com.knoxx.mycollection.service;

import com.knoxx.mycollection.entity.Item;
import com.knoxx.mycollection.exception.EntityNotFoundException;
import com.knoxx.mycollection.exception.ResourceNotFoundException;
import com.knoxx.mycollection.model.ItemDto;
import com.knoxx.mycollection.repository.ItemRepository;
import com.knoxx.mycollection.utils.ItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ItemService {

    private final ItemRepository repository;

    private final ItemMapper itemMapper;

    public ItemService(ItemRepository repository, ItemMapper itemMapper) {
        this.repository = repository;
        this.itemMapper = itemMapper;
    }

    public List<ItemDto> findAll() throws ResourceNotFoundException {
        List<Item> list = repository.findAll();

        if (list.isEmpty()) {
            throw new ResourceNotFoundException(Item.class, "Message={No items found}");
        }

        return itemMapper.toDtoList(list);
    }

    public ItemDto findByItemId(String itemId) {
        return itemMapper.toDto(repository.findByItemId(itemId)
                .orElseThrow(() -> new EntityNotFoundException(Item.class, "itemId", itemId)));
    }

    public ItemDto save(ItemDto item) {
        return itemMapper.toDto(repository.save(itemMapper.toEntity(item)));
    }

    public ItemDto updateItem(ItemDto item) {
        return this.save(item);
    }


    public Boolean removeItem(String itemId) {
        try {
            repository.deleteById(itemId);
            return true;
        } catch (Exception e) {
            log.error("Error deleting item {}", itemId);
            log.error(e.getMessage());
            return false;
        }
    }

    public List<ItemDto> findAllByCategoryId(String categoryId) throws ResourceNotFoundException {
        List<Item> list = repository.findByCategoryId(categoryId);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException(Item.class, "Message={No items found for category " + categoryId + "}");
        }

        return itemMapper.toDtoList(list);
    }

}