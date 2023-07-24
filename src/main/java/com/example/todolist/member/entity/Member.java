package com.example.todolist.member.entity;

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
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long memberNo;

    @Column(nullable = false)
    String memberEmail;

    @Column(nullable = false)
    String memberPwd;

    @Column(nullable = false)
    String memberName;

    @Column(nullable = false)
    String memberNickname;



}
