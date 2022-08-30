package com.LittleTown.User.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserLoginResponseDto {
    public Long userIdx;
    public Long clothIdx;
    public Long money;

    @Builder
    public UserLoginResponseDto (Long userIdx, Long clothIdx, Long money) {
        this.userIdx = userIdx;
        this.clothIdx = clothIdx;
        this.money = money;
    }
}
