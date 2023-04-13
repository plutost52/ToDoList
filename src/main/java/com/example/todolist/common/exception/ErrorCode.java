package com.example.todolist.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SERVER_ERROR("알 수 없는 서버 에러가 발생했습니다.", 500);

    private final String msg;
    private final int statusCode;
}
