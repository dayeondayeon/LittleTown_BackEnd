package com.LittleTown.User.dto;
import com.LittleTown.User.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String id;
    private String pw;

    @Builder
    public UserLoginRequestDto(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public boolean isEmpty() {
        if (this.id.isEmpty() || this.pw.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}
