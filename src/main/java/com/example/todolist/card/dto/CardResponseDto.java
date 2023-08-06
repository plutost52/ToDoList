package com.example.todolist.card.dto;

import com.example.todolist.cardLine.dto.CardLineDto;
import com.example.todolist.member.entity.Member;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDto {

    private Long cardNo;
    private String cardTitle;
    private Boolean cardDone;
    private String createdAt;
    private Long memberNo;
    private List<CardLineDto> cardLine;

}
