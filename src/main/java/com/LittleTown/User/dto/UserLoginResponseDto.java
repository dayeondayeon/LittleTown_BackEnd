package com.LittleTown.User.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserLoginResponseDto {
    public Long userIdx;

    @Builder
    public UserLoginResponseDto (Long userIdx) {
        this.userIdx = userIdx;
    }
}
