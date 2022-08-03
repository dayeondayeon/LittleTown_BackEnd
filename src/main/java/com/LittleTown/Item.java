package com.LittleTown;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Item {
    private long itemIdx;
    private long itemCount;

    @Builder
    public Item(long itemIdx, long itemCount) {
        this.itemIdx = itemIdx;
        this.itemCount = itemCount;
    }
}
