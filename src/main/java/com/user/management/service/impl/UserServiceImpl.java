package com.user.management.service.impl;

import com.user.management.dto.UserDto;
import com.user.management.dto.UserResponseDto;
import com.user.management.entity.UserJpaEntity;
import com.user.management.repository.UserJpaRepository;
import com.user.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserJpaRepository userJpaRepository;

    @Override
    public UserResponseDto createUser(UserDto userDto) {

        UserJpaEntity userJpaEntity = new UserJpaEntity();
        userJpaEntity.setName(userDto.getName());

        UserJpaEntity save = userJpaRepository.save(userJpaEntity);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(save.getId());
        userResponseDto.setName(save.getName());
        return userResponseDto;
    }

    @Override
    public UserResponseDto findById(Long id) {
        Optional<UserJpaEntity> byId = userJpaRepository.findById(id);
        if(byId.isEmpty()) {
            throw new RuntimeException("Not Found");
        }
        UserJpaEntity userJpaEntity = byId.get();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userJpaEntity.getId());
        userResponseDto.setName(userJpaEntity.getName());
        return userResponseDto;
    }
}
