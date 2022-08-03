package com.LittleTown.User.dto;

import com.LittleTown.Item;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class UserSaveRequestDto {
    private long clothes;
    private long money;
    List<Item> pocket;

    @Builder
    public UserSaveRequestDto(long clothes, long money, List<Item> pocket) {
        this.clothes = clothes;
        this.money = money;
        this.pocket = pocket;
    }
}
