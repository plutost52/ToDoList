package com.example.todolist.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDto {
    private Long memberNo;
    private String memberEmail;
    private String memberPwd;
    private String memberName;
    private String memberNickname;
}
