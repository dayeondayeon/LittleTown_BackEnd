package com.LittleTown.Email.controller;

import com.LittleTown.Email.dto.EmailReceiveResponseDto;
import com.LittleTown.Email.dto.EmailSendRequestDto;
import com.LittleTown.Email.service.EmailServiceImpl;
import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/email", consumes = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {
    private final EmailServiceImpl emailService;
    @PostMapping("/write")
    public ResponseEntity send(@RequestBody EmailSendRequestDto emailSendRequestDto) {
        try {
            ResponseDto responseDto = emailService.send(emailSendRequestDto);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, responseDto.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }

    @GetMapping("/list/{userIdx}")
    public ResponseEntity getList(@PathVariable long userIdx) {
        try {
            EmailReceiveResponseDto responseDto = emailService.receive(userIdx);
            return ResponseEntity.ok().body(ResponseDto.res(Status.OK, Message.EMAIL_LIST_SUCCESS, responseDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDto.res(Status.BAD_REQUEST, e.getMessage()));
        }
    }
}
