package com.LittleTown.User.dto;

import com.LittleTown.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class UserJoinRequestDto {
    private String id;
    private String pw;
    private String nickname;
    PasswordEncoder passwordEncoder;

    @Builder
    public UserJoinRequestDto(String id, String pw, String nickname) {
        this.id = id;
        this.pw = encodePw(pw);
        this.nickname = nickname;
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .pw(pw)
                .nickname(nickname)
                .clothes(1L)
                .money(0L)
                .build();
    }

    public boolean isEmpty() {
        if (this.id.isEmpty() || this.pw.isEmpty() || this.nickname.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    private String encodePw(String pw) {
        return passwordEncoder.encode(pw);
    }

}