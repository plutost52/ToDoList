package com.example.todolist.card.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CardDto {

    private Long cardNo;
    private Long memberNo;
    private String cardTitle;
    private Boolean cardDone;
    private String cardCreateAt;

    public CardDto() {

    }

    public CardDto(Long cardNo, Long memberNo, String cardTitle, Boolean cardDone, String cardCreateAt) {
        this.cardNo = cardNo;
        this.memberNo = memberNo;
        this.cardTitle = cardTitle;
        this.cardDone = cardDone;
        this.cardCreateAt = cardCreateAt;
    }

}
