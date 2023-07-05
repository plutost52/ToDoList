package com.example.todolist.cardLine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CardLineDto {

    private Long lineNo;
    private Long cardNo;
    private Long cardLineChecked;
    private String cardLineValue;

    public CardLineDto(Long lineNo, Long cardNo, Long cardLineChecked, String cardLineValue) {
        this.lineNo = lineNo;
        this.cardNo = cardNo;
        this.cardLineChecked = cardLineChecked;
        this.cardLineValue = cardLineValue;
    }

    @Override
    public String toString() {
        return "lineNo=" + lineNo +
                ", cardNo=" + cardNo +
                ", cardLineChecked=" + cardLineChecked +
                ", cardLineValue='" + cardLineValue + '\'';
    }

}
