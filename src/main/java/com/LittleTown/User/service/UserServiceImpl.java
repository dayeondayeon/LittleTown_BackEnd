package com.LittleTown.User.service;

import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.User.domain.User;
import com.LittleTown.User.domain.UserRepository;
import com.LittleTown.User.dto.UserJoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDto join(UserJoinRequestDto userJoinRequestDto) throws Exception {
        if (userJoinRequestDto.isEmpty() == true) {
            throw new Exception(Message.MISSING_ARGUMENT);
        } else {
            Optional<User> idCheck = userRepository.findById(userJoinRequestDto.getId());
            Optional<User> nicknameCheck = userRepository.findByNickname(userJoinRequestDto.getNickname());

            if (idCheck.isEmpty() == false) {
                throw new Exception(Message.ALREADY_EXIST_ID);
            }
            else if (nicknameCheck.isEmpty() == false) {
                throw new Exception(Message.ALREADY_EXIST_NICKNAME);
            }
            else {


                return new ResponseDto(Status.OK, Message.JOIN_SUCCESS);
            }
        }
    }
}
