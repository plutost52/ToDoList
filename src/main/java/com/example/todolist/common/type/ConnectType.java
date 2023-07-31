package com.example.todolist.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConnectType {
    LOGIN("로그인"),
    LOGOUT("로그아웃");

    String connectType;
}
