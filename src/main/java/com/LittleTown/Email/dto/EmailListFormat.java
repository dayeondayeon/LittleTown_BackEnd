package com.LittleTown.Email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailListFormat {
    private String receiverNickname;
    private String senderNickname;
    private String contents;

    @Builder
    public EmailListFormat (String receiverNickname, String senderNickname, String contents) {
        this.receiverNickname = receiverNickname;
        this.senderNickname = senderNickname;
        this.contents = contents;
    }
}
