package com.example.todolist.card.dto;

import com.example.todolist.cardLine.dto.CardLineDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CardDto {

    private Long cardNo;
    private Long memberNo;
    private String cardTitle;
    private Boolean cardDone;
    private String cardCreateAt;
    private List<CardLineDto> cardLine;

    public CardDto() {

    }

    public CardDto(Long cardNo, Long memberNo, String cardTitle, Boolean cardDone, String cardCreateAt) {
        this.cardNo = cardNo;
        this.memberNo = memberNo;
        this.cardTitle = cardTitle;
        this.cardDone = cardDone;
        this.cardCreateAt = cardCreateAt;
    }

    @Override
    public String toString() {

        String result = "CardDto {" +
                "\ncardNo=" + cardNo +
                "\nmemberNo=" + memberNo +
                "\ncardTitle='" + cardTitle + '\'' +
                "\ncardDone=" + cardDone +
                "\ncardCreateAt='" + cardCreateAt + '\'' +
                "\ncardLine=[ ";

        String cardLines = "";
        if (cardLine != null) {
            for (Long i = 0L; i.intValue() < cardLine.size(); i++)
                cardLines += "\n    " + i + " : " + cardLine.get(i.intValue()).toString();
        }
        result += cardLines + " ] }";

        return result;
    }

}
