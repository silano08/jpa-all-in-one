package com.jpaprac.jpapractice.controller;

import com.jpaprac.jpapractice.model.dto.UserRequestDto;
import com.jpaprac.jpapractice.model.dto.UserResponseDto;
import com.jpaprac.jpapractice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User API", description = "사용자 관리 API")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "사용자 목록 조회", description = "모든 사용자를 조회합니다.")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자 조회", description = "ID를 기반으로 사용자를 조회합니다.")
    public UserResponseDto getUserById(
            @Parameter(description = "조회할 사용자 ID", example = "1")
            @PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    public UserResponseDto createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "생성할 사용자 정보")
            @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.getEmail();
        return userService.createUser(userRequestDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "사용자 수정", description = "기존 사용자를 수정합니다.")
    public UserResponseDto updateUser(
            @Parameter(description = "수정할 사용자 ID", example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "수정할 사용자 정보")
            @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "사용자 삭제", description = "ID를 기반으로 사용자를 삭제합니다.")
    public void deleteUser(
            @Parameter(description = "삭제할 사용자 ID", example = "1")
            @PathVariable Long id) {
        userService.deleteUser(id);
    }
}
