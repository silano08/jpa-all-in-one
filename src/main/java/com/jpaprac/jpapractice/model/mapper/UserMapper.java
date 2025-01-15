package com.jpaprac.jpapractice.model.mapper;

import com.jpaprac.jpapractice.model.dto.UserDTO;
import com.jpaprac.jpapractice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);
}