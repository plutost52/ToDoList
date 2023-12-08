package com.example.todolist.member.entity;

import com.example.todolist.common.TimeStamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Member extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(nullable = false)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPwd;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false)
    private String memberNickname;

    @Column
    @Builder.Default
    private int logInTryCount = 0;

    @Column
    private LocalDateTime lockedAt;

    public void update(String memberPwd, String memberNickname){
        this.memberPwd = memberPwd == null ? this.memberPwd : memberPwd;
        this.memberNickname = memberNickname == null ? this.memberNickname : memberNickname;
    }

    public void logInTryCountUpdate(boolean correct){
        this.logInTryCount = correct ? 0 : this.logInTryCount+1;
    }

    public void lockMember(){
        this.lockedAt = LocalDateTime.now();
    }

    public void unLockMember(){
        this.lockedAt = null;
    }

}
