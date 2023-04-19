package com.example.todolist.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SERVER_ERROR("알 수 없는 서버 에러가 발생했습니다.", 500),

    CARD_CREATE_FAILED("카드 생성 실패", 500),

    CARD_DELETE_BADREQUEST("삭제 대상 정보 없음", 400),
    CARD_DELETE_FAILED("카드 삭제 실패", 500),

    CARD_LIST_BADREQUEST("사용자 정보 없음", 400),
    CARD_LIST_FAILED("카드 목록 조회 실패", 500);

    private final String msg;
    private final int statusCode;
}
