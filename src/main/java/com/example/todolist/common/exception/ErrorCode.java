package com.example.todolist.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    SERVER_ERROR("알 수 없는 서버 에러가 발생했습니다.", 500),
    BAD_REQUEST_ERROR("잘못 된 요청입니다.", 400),
    AUTH_FAIL("해당 작업에 대한 권한이 없습니다.", 403),
    EMAIL_DUPLICATED("이미 존재하는 이메일입니다.", 500),
    NICKNAME_DUPLICATED("이미 존재하는 닉네임입니다.", 500),
    MEMBER_NOT_FOUND("알 수 없는 사용자입니다.", 404),
    TOKEN_ERROR("토큰이 유효하지 않습니다.",401),
    FORBIDDEN_ERROR("서버 사용 권한이 없습니다.",403),
    NOT_FOUND_EMAIL("존재하지 않는 이메일입니다.", 404),
    INCORRECT_PASSWORD("비밀번호가 틀렸습니다.", 404),
    LOCKED_MEMBER("해당 아이디는 잠금 처리되었습니다.", 400),
    FRIEND_DUPLICATED("이미 등록 된 친구 입니다.", 400),
    FRIEND_NOT_FOUND("친구를 찾을 수 없습니다.", 400),
    SEARCH_KEYWORD_NOT_BLACK("검색어는 공백이 될 수 없습니다.", 400),

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
