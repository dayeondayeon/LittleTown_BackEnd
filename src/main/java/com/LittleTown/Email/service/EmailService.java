package com.LittleTown.Email.service;

import com.LittleTown.Email.dto.EmailReceiveResponseDto;
import com.LittleTown.Email.dto.EmailSendRequestDto;
import com.LittleTown.ResponseDto;

public interface EmailService {
    public ResponseDto send(EmailSendRequestDto emailSendRequestDto) throws Exception;
    public EmailReceiveResponseDto receive(long userIdx) throws Exception;
}
