package com.example.todolist.card.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CardRequestDto {

    private Boolean cardDone;
    private Long[] cardNo;
    private String cardTitle;

}
