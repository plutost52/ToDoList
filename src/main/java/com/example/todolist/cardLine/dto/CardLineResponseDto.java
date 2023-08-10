package com.example.todolist.cardLine.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CardLineResponseDto {
    private Long cardLineNo;
    private Long lineNo;
    private Boolean cardLineChecked;
    private String cardLineValue;
}
