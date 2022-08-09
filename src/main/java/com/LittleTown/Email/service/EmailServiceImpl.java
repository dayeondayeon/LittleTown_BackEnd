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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;

    @Override
    @Transactional
    public ResponseDto send(EmailSendRequestDto emailSendRequestDto) throws Exception{
        if (emailSendRequestDto.isEmpty()) {
            throw new Exception(Message.MISSING_ARGUMENT);
        }
        else {
            try {
                Optional<User> sender = userRepository.findById(emailSendRequestDto.getSender());
                Optional<User> receiver = userRepository.findById(emailSendRequestDto.getReceiver());

                if (sender.isEmpty() || receiver.isEmpty()) {
                    throw new Exception(Message.INVALID_USER);
                }
                emailRepository.save(emailSendRequestDto.toEntity());
                return new ResponseDto(Status.OK, Message.EMAIL_SEND_SUCCESS);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public EmailReceiveResponseDto receive(long userIdx) throws Exception {
        try {
            Optional<User> user = userRepository.findById(userIdx);
            if (user.isEmpty()) {
                throw new Exception(Message.INVALID_USER);
            }
            else {
                List<Email> emails = emailRepository.findByReceiver(userIdx); // 받는 사람 userIdx 인 이메일목록 불러오기
                List<EmailListFormat> responseEmails = new ArrayList<>();
                String receiver = user.get().getNickname();
                for (Email e : emails) {
                    if (userRepository.findById(e.getSender()).isEmpty()) {
                        throw new Exception(Message.INVALID_USER);
                    }
                    else {
                        String sender = userRepository.findById(e.getSender()).get().getNickname(); // 보낸 사람 닉네임 찾기.
                        EmailListFormat emailListFormat = new EmailListFormat(receiver, sender, e.getContents());
                        responseEmails.add(emailListFormat);
                    }
                }
                return new EmailReceiveResponseDto(responseEmails);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
