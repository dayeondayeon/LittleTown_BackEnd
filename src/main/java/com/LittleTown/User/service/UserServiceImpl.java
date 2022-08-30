package com.LittleTown.User.service;

import com.LittleTown.Message;
import com.LittleTown.ResponseDto;
import com.LittleTown.Status;
import com.LittleTown.User.domain.User;
import com.LittleTown.User.domain.UserRepository;
import com.LittleTown.User.dto.UserJoinRequestDto;
import com.LittleTown.User.dto.UserLoginRequestDto;
import com.LittleTown.User.dto.UserLoginResponseDto;
import com.LittleTown.User.dto.UserSaveRequestDto;
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
            try {
                Optional<User> idCheck = userRepository.findById(userJoinRequestDto.getId());
                Optional<User> nicknameCheck = userRepository.findByNickname(userJoinRequestDto.getNickname());

                if (idCheck.isEmpty() == false) {
                    throw new Exception(Message.ALREADY_EXIST_ID);
                }
                if (nicknameCheck.isEmpty() == false) {
                    throw new Exception(Message.ALREADY_EXIST_NICKNAME);
                }
                userRepository.save(userJoinRequestDto.toEntity());
                return new ResponseDto(Status.OK, Message.JOIN_SUCCESS);
            }
            catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ResponseDto login(UserLoginRequestDto userLoginRequestDto) throws Exception {
        if (userLoginRequestDto.isEmpty() || userLoginRequestDto.getId().isEmpty()) {
            throw new Exception(Message.MISSING_ARGUMENT);
        }
        else {
            try {
                Optional<User> idCheck = userRepository.findById(userLoginRequestDto.getId());
                if (idCheck.isEmpty()) {
                    throw new Exception(Message.INVALID_USER);
                }
                else if (!idCheck.get().getId().equals(userLoginRequestDto.getId()) || !idCheck.get().getPw().equals(userLoginRequestDto.getPw())) {
                    throw new Exception(Message.ID_PW_ERROR);
                } else {
                    UserLoginResponseDto responseDto = new UserLoginResponseDto(idCheck.get().getUseridx(), idCheck.get().getClothes(), idCheck.get().getMoney());
                    return new ResponseDto(Status.OK, Message.LOGIN_SUCCESS, responseDto);
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }

    @Override
    public ResponseDto save(UserSaveRequestDto userSaveRequestDto) throws Exception {
        return null;
    }

}
