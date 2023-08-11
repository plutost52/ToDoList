package com.example.todolist.cardLine.entity;

import com.example.todolist.card.entity.Card;
import com.example.todolist.common.TimeStamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CardLine extends TimeStamp {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardLineNo;

    private Long lineNo;

    @Column
    private Boolean cardLineChecked;

    @Column String cardLineValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardNo", nullable = false)
    private Card card;

    public void checkCardLine(){
        this.cardLineChecked = !this.cardLineChecked;
    }
    public void updateCardLineValue(String cardLineValue){
        this.cardLineValue = cardLineValue;
    }
}
