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

    CARD_READ_BADREQUEST("조회 대상 정보 없음", 400),
    CARD_READ_FAILED("카드 조회 실패", 500),

    CARD_LIST_BADREQUEST("조회 대상 정보 없음", 400),
    CARD_LIST_FAILED("카드 목록 조회 실패", 500),

    CARD_UPDATE_TITLE_BADREQUEST("수정 대상 정보 없음", 400),
    CARD_UPDATE_TITLE_FAILED("카드 제목 저장 실패", 500),

    CARD_UPDATE_DONE_BADREQUEST("수정 대상 정보 없음", 400),
    CARD_UPDATE_DONE_FAILED("카드 상태변경 실패", 500);

    private final String msg;
    private final int statusCode;
}
