package com.user.management.controller;

import com.user.management.dto.UserDto;
import com.user.management.dto.UserResponseDto;
import com.user.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
