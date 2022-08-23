package com.LittleTown.Storage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetItemRequestDto {
    private long userIdx;
    private long itemIdx;

    @Builder
    public GetItemRequestDto(long userIdx, long itemIdx) {
        this.userIdx = userIdx;
        this.itemIdx = itemIdx;
    }

}
