package com.jpaprac.jpapractice.service;

import com.jpaprac.jpapractice.model.dto.UserRequestDto;
import com.jpaprac.jpapractice.model.dto.UserResponseDto;
import com.jpaprac.jpapractice.model.entity.User;
import com.jpaprac.jpapractice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("modelmapper")
public class ModelMapperUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserResponseDto> getAllUsers() {
        // Entity -> Response DTO 변환
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // Request DTO -> Entity 변환
        User user = modelMapper.map(userRequestDto, User.class);
        User savedUser = userRepository.save(user);
        // Entity -> Response DTO 변환
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        return userRepository.findById(id).map(existingUser -> {
            // Request DTO -> Entity 변환 및 ID 유지
            modelMapper.map(userRequestDto, existingUser); // 기존 엔티티에 값 복사
            User savedUser = userRepository.save(existingUser);
            // Entity -> Response DTO 변환
            return modelMapper.map(savedUser, UserResponseDto.class);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
