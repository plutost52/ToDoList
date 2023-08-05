package com.example.todolist.card.entity;

import com.example.todolist.common.TimeStamp;
import com.example.todolist.member.entity.Member;
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
public class Card extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardNo;

    @Column
    private String cardTitle;

    @Column
    private Boolean cardDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo", nullable = false)
    private Member member;

    public void updateCardTitle(String cardTitle){
        this.cardTitle = cardTitle;
    }
}
