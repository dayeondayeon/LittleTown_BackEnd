package com.LittleTown.Email.service;

import com.LittleTown.Email.domain.Email;
import com.LittleTown.Email.domain.EmailRepository;
import com.LittleTown.Email.dto.EmailListFormat;
import com.LittleTown.Email.dto.EmailReceiveResponseDto;
import com.LittleTown.Email.dto.EmailSendRequestDto;
import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.User.domain.User;
import com.LittleTown.User.domain.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmailServiceImpl implements EmailService {
    UserRepository userRepository;
    EmailRepository emailRepository;

    @Override
    public ResponseDto send(EmailSendRequestDto emailSendRequestDto) throws Exception{
        long senderIdx = emailSendRequestDto.getSender();
        long receiverIdx = emailSendRequestDto.getReceiver();

        Optional<User> sender = userRepository.findById(senderIdx);
        Optional<User> receiver = userRepository.findById(receiverIdx);

        if (sender.isEmpty() || receiver.isEmpty()) {
            throw new Exception(Message.INVALID_USER);
        }
        else if (emailSendRequestDto.getContents().isEmpty()) {
            throw new Exception(Message.MISSING_ARGUMENT);
        }
        else {
            emailRepository.save(emailSendRequestDto.toEntity());
            return new ResponseDto(Status.OK, Message.EMAIL_SEND_SUCCESS);
        }
    }

    @Override
    public EmailReceiveResponseDto receive(long userIdx) throws Exception {
        Optional<User> user = userRepository.findById(userIdx);

        if (user.isEmpty()) {
            throw new Exception(Message.INVALID_USER);
        }
        else {
            List<Email> emails = emailRepository.findByWriter(userIdx);
            List<EmailListFormat> responseEmails = new ArrayList<>();
            String nickname = user.get().getNickname();
            for (Email e : emails) {
                EmailListFormat emailListFormat = new EmailListFormat(nickname, e.getContents());
            }
            return new EmailReceiveResponseDto(responseEmails);
        }
    }

}
