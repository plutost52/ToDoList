package com.example.todolist.cardLine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class CardLineDto {

    private Long cardNo;
    private Boolean cardLineChecked;
    private String cardLineValue;

}
