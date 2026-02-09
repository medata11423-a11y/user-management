package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService service;

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserDto userDto) {
        return service.createUser(userDto);
    }

    @GetMapping
    public String health() {
        return "Running";
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        //throw new BadRequestException("Exception is thrown");
        return service.findById(id);
    }

}
