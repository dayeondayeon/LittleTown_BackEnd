package com.LittleTown.User.service;

import com.LittleTown.ResponseDto;
import com.LittleTown.User.dto.UserJoinRequestDto;

public interface UserService {
    public ResponseDto join(UserJoinRequestDto userJoinRequestDto) throws Exception;
}
