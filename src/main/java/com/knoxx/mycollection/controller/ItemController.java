package com.knoxx.mycollection.controller;

import com.knoxx.mycollection.model.ItemDto;
import com.knoxx.mycollection.service.ItemService;
import com.knoxx.mycollection.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping(path = "/item/items")
    public ResponseEntity<List<ItemDto>> findAll() {
        log.info("findAll()");

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/item/items/{itemId}")
    public ResponseEntity<ItemDto> get(@PathVariable("itemId") String itemId) {
        log.info("requested for item id [{}]", itemId);

        return new ResponseEntity<>(service.findByItemId(itemId), HttpStatus.OK);
    }

    @PostMapping(path = "/item/items")
    public ResponseEntity<ItemDto> addItem(@Valid @RequestBody ItemDto item) {
        log.info("Adding item [{}]", item.getItemName());

        item.setPublishDate(DateUtils.getCurrentDate());
        return new ResponseEntity<>(service.save(item), HttpStatus.CREATED);
    }

    @PutMapping(path = "/item/items")
    public ResponseEntity<ItemDto> update(@Valid @RequestBody ItemDto item) {
        log.info("Updating item [{}]", item.getItemName());

        return new ResponseEntity<>(service.updateItem(item), HttpStatus.OK);
    }

    @DeleteMapping(path = "/item/items/{itemId}")
    public ResponseEntity<Boolean> delete(@PathVariable("itemId") String itemId) {
        log.info("Deleting item [{}]", itemId);

        return new ResponseEntity<>(service.removeItem(itemId), HttpStatus.OK);
    }

    @GetMapping(path = "/item/itemsByCategory/{categoryId}")
    public ResponseEntity<List<ItemDto>> findAllItemsByCategory(@PathVariable("categoryId") String categoryId) {
        log.info("find all items by category [{}]", categoryId);

        return new ResponseEntity<>(service.findAllByCategoryId(categoryId), HttpStatus.OK);
    }

}
