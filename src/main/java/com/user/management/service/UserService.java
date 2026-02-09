package com.user.management.service;

import com.user.management.dto.UserDto;
import com.user.management.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserDto userDto);

    UserResponseDto findById(Long id);
}
