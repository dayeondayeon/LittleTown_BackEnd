package com.LittleTown.Storage.dto;

import com.LittleTown.Storage.domain.Storage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PutRequestDto {
    private long userIdx;
    private long itemIdx;
    private long count;
    private int itemType;
    private int storageType;

    @Builder
    public PutRequestDto(long userIdx, long itemIdx, long count, int itemType, int storageType) {
        this.userIdx = userIdx;
        this.itemIdx = itemIdx;
        this.count = count;
        this.itemType = itemType;
        this.storageType = storageType;
    }

    public Storage toEntity() {
        return Storage.builder()
                .userIdx(userIdx)
                .itemIdx(itemIdx)
                .count(count)
                .storageType(storageType)
                .build();
    }
}
