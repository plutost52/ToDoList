package com.example.todolist.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageCode {

    SUCCESS("성공", 200),

    CARD_CREATE_SUCCESS("카드 추가 성공", 200),

    CARD_DELETE_SUCCESS("카드 삭제 성공", 200),

    CARD_LIST_SUCCESS("카드 목록 조회 성공", 200);

    private final String msg;
    private final int statusCode;
}
