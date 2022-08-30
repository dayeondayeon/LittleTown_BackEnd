package com.LittleTown.User.controller;

import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.User.dto.UserJoinRequestDto;
import com.LittleTown.User.dto.UserLoginRequestDto;
import com.LittleTown.User.dto.UserLoginResponseDto;
import com.LittleTown.User.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserServiceImpl userService;
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        try {
            ResponseDto responseDto = userService.join(userJoinRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        try {
            ResponseDto responseDto = userService.login(userLoginRequestDto);

            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, responseDto.getMessage(), responseDto.getData()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }
}
