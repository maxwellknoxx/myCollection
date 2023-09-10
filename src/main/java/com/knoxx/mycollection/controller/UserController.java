package com.knoxx.mycollection.controller;

import com.knoxx.mycollection.model.UserDto;
import com.knoxx.mycollection.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(path = "/user/users")
    public ResponseEntity<List<UserDto>> findAll() {
        log.info("FindAll()");

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/user/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        log.info("requested for user id [{}]", id);

        return new ResponseEntity<>(service.findByUserId(id), HttpStatus.OK);
    }

}
