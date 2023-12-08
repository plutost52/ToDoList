package com.example.todolist.card.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class CardRequestDto {

    private Boolean cardDone;
    private List<Long> cardNo;
    private String cardTitle;

}
