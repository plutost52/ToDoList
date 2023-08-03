package com.example.todolist.card.entity;

import com.example.todolist.common.TimeStamp;
import com.example.todolist.member.entity.Member;

import javax.persistence.*;

public class Card extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardNo;

    private String cartTitle;

    private Boolean cardDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo", nullable = false)
    private Member member;
}
