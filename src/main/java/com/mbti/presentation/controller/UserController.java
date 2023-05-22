
package com.mbti.presentation.controller;

import com.mbti.application.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mbti.presentation.dto.Response;
import com.mbti.presentation.dto.UserDto;

import java.util.List;

@Tag(name="회원관리", description = "회원관련 api입니다.")
@RequiredArgsConstructor   // 필드자동생성자주입
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입 메소드", description = "회원가입 메소드입니다.")
    @PostMapping("/register")
    public ResponseEntity<Response<UserDto.RegisterResponseDto>> register(@RequestBody UserDto.UserRequestDto userRequestDto) {

        UserDto.RegisterResponseDto user = userService.register(userRequestDto);
        return ResponseEntity.ok().body(Response.success(new UserDto.RegisterResponseDto(user.getId(),user.getNick(),user.getMbti())));
    }
    @Operation(summary = "로그아웃 메소드",description = "로그아웃 메소드입니다.")
    @PostMapping("/min")
    public ResponseEntity<Response<HttpStatus>> refresh(@RequestBody UserDto.UserLogoutRequest userLogoutRequest) {
        System.out.println("method called");
        userService.refresh(userLogoutRequest);
        return ResponseEntity.ok().body(Response.success(HttpStatus.OK));
    }
    @Operation(summary = "로그인 메소드", description = "로그인 메소드입니다.")
    @PostMapping("/login")
    public ResponseEntity<Response<UserDto.UserLoginResponse>> login(@RequestBody UserDto.UserRequestDto userRequestDto) {

       UserDto.UserLoginResponse user = userService.login(userRequestDto);
        return ResponseEntity.ok().body(Response.success(new UserDto.UserLoginResponse(user.getToken(), user.getMbti())));
    }

    @Operation(summary = "로그인 회원 전체조회 메소드", description = "로그인 회원조회 메소드입니다.")
    @GetMapping("/login/userlist")
    public List<UserDto.UserLoginListResponse>loginSearchAll() {
        return userService.loginSearchAll();
    }

}
