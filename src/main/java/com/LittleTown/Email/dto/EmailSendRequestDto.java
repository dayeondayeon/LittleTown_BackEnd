package com.LittleTown.Email.dto;

import com.LittleTown.Email.domain.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailSendRequestDto {
    private long sender;
    private long receiver;
    private String contents;

    @Builder
    public EmailSendRequestDto(long sender, long receiver, String contents) {
        this.sender = sender;
        this.receiver = receiver;
        this.contents = contents;
    }

    public Email toEntity() {
        return Email.builder()
                .sender(sender)
                .receiver(receiver)
                .contents(contents)
                .build();
    }

    public boolean isEmpty() {
        if (this.contents.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
