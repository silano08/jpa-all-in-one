package com.jpaprac.jpapractice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String name;
    private String email;

    // Getters and Setters
}