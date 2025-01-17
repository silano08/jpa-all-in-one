package com.jpaprac.jpapractice.service;

import com.jpaprac.jpapractice.model.dto.UserRequestDto;
import com.jpaprac.jpapractice.model.dto.UserResponseDto;
import com.jpaprac.jpapractice.model.entity.User;
import com.jpaprac.jpapractice.model.mapper.UserMapper;
import com.jpaprac.jpapractice.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("mapstruct")
public class MapStructExampleUserService extends UserServiceImpl {

    private final UserRepository userRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    public MapStructExampleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User user = userMapper.toEntity(userRequestDto);
        User savedUser = userRepository.save(user);
        return userMapper.toResponseDto(savedUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        return userRepository.findById(id).map(existingUser -> {
            // 기존 객체에만 필요한 필드 복사
            userMapper.updateEntityFromDto(userRequestDto, existingUser);
            User savedUser = userRepository.save(existingUser);
            return userMapper.toResponseDto(savedUser);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
