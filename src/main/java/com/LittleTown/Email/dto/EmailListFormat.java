package com.LittleTown.Email.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailListFormat {
    private String nickname;
    private String contents;

    @Builder
    public EmailListFormat (String nickname, String contents) {
        this.nickname = nickname;
        this.contents = contents;
    }
}
