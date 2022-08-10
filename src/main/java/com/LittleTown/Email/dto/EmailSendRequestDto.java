package com.LittleTown.Email.dto;

import com.LittleTown.Email.domain.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendRequestDto {
    private long sender;
    private String receiverName;
    private String contents;

    @Builder
    public EmailSendRequestDto(long sender, String receiverName, String contents) {
        this.sender = sender;
        this.receiverName = receiverName;
        this.contents = contents;
    }

    public boolean isEmpty() {
        if (this.contents.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
