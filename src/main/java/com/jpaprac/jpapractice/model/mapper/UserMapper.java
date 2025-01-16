package com.jpaprac.jpapractice.model.mapper;

import com.jpaprac.jpapractice.model.dto.UserRequestDto;
import com.jpaprac.jpapractice.model.dto.UserResponseDto;
import com.jpaprac.jpapractice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDto requestDto);

    UserResponseDto toResponseDto(User user);


    // DTO에서 기존 엔티티로 필드 복사
    void updateEntityFromDto(UserRequestDto dto, @MappingTarget User entity);
}