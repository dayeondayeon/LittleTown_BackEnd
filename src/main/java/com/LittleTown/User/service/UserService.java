package com.LittleTown.User.service;

import com.LittleTown.ResponseDto;
import com.LittleTown.User.dto.UserJoinRequestDto;
import com.LittleTown.User.dto.UserLoginRequestDto;
import com.LittleTown.User.dto.UserLoginResponseDto;
import com.LittleTown.User.dto.UserSaveRequestDto;

public interface UserService {
    public ResponseDto join(UserJoinRequestDto userJoinRequestDto) throws Exception;
    public ResponseDto login(UserLoginRequestDto userLoginRequestDto) throws Exception;
    public ResponseDto save(UserSaveRequestDto userSaveRequestDto) throws Exception;
}
