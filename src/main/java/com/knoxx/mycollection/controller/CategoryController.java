package com.knoxx.mycollection.controller;

import com.knoxx.mycollection.model.CategoryDto;
import com.knoxx.mycollection.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/category/categories")
    public ResponseEntity<List<CategoryDto>> findAll() {
        log.info("findAll()");

        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/category/categories/{id}")
    public ResponseEntity<CategoryDto> findByCategoryId(@PathVariable("id") String id) {
        log.info("requested for category id [{}]", id);

        return new ResponseEntity<>(categoryService.findByCategoryId(id), HttpStatus.OK);
    }

    @PostMapping(path = "/categoryDTO/categories")
    public ResponseEntity<CategoryDto> addCategory(@Valid @RequestBody CategoryDto categoryDTO) {
        log.info("Adding category [{}]", categoryDTO.getName());

        return new ResponseEntity<>(categoryService.save(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/categoryDTO/categories")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDTO) {
        log.info("Updating Category [{}]", categoryDTO.getName());

        return new ResponseEntity<>(categoryService.updateCategory(categoryDTO), HttpStatus.OK);
    }

    @DeleteMapping(path = "/category/categories/{categoryId}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("categoryId") String categoryId) {
        log.info("Deleting Category [{}]", categoryId);

        return new ResponseEntity<>(categoryService.removeCategory(categoryId), HttpStatus.OK);
    }

}
