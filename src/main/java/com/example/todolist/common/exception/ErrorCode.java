package com.example.todolist.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SERVER_ERROR("알 수 없는 서버 에러가 발생했습니다.", 500),
    EMAIL_DUPLICATED("이미 존재하는 이메일입니다.", 500),
    NICKNAME_DUPLICATED("이미 존재하는 닉네임입니다.", 500),
    MEMBER_NOT_FOUND("알 수 없는 사용자입니다.", 404),
    TOKEN_ERROR("토큰이 유효하지 않습니다.",401),
    FORBIDDEN_ERROR("서버 사용 권한이 없습니다.",403),
    NOT_FOUND_EMAIL("존재하지 않는 이메일입니다.", 404),
    INCORRECT_PASSWORD("비밀번호가 틀렸습니다.", 404);

    private final String msg;
    private final int statusCode;
}
