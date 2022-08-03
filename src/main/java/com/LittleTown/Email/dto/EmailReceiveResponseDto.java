package com.LittleTown.Email.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import java.util.List;

@Data
@Getter
public class EmailReceiveResponseDto {
    List<EmailListFormat> emailList;

    @Builder
    public EmailReceiveResponseDto(List<EmailListFormat> emailList) {
        this.emailList = emailList;
    }
}
