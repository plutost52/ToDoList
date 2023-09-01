package com.example.todolist.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private Long memberNo;
    @Email(message = "이메일 형식이 맞지 않습니다.")
    private String memberEmail;
    //영어 대문자, 소문자, 숫자, 특수기호 하나씩 필수 8~20글자
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "비밀번호 형식이 맞지 않습니다. ")
    private String memberPwd;
    //영어 대소문자, 한글만 허용 2~20글자
    @Pattern(regexp = "^[a-zA-Z가-힣]{2,20}$", message = "이름 형식이 맞지 않습니다.")
    private String memberName;
    //영어 대소문자, 한글, 숫자 허용 2~20글자
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{2,20}$", message = "닉네임 형식이 맞지 않습니다.")
    private String memberNickname;

    public void updatePwdEncoding(String encodingPwd){
        this.memberPwd = encodingPwd;
    }
}
