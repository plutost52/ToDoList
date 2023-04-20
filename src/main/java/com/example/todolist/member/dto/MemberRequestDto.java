package com.example.todolist.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String memberEmail;
    private String memberPwd;
    private String memberName;
    private String memberNickname;

    public void updatePwdEncoding(String encodingPwd){
        this.memberPwd = encodingPwd;
    }
}
