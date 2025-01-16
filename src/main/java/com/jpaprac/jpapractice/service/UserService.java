package com.jpaprac.jpapractice.service;

import com.jpaprac.jpapractice.model.dto.UserRequestDto;
import com.jpaprac.jpapractice.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserById(Long id);
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);
    void deleteUser(Long id);
}
