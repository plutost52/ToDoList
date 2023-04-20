package com.example.todolist.member.controller;

import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member")
    public ResponseMessage<?> createMember(@RequestBody MemberRequestDto memberRequestDto){
        memberService.createMember(memberRequestDto);
        return new ResponseMessage<>(MessageCode.SUCCESS, null);
    }

    @PostMapping(value = "/member/login")
    public ResponseMessage<?> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response){
        memberService.login(memberRequestDto, response);
        return new ResponseMessage<>(MessageCode.SUCCESS, null);
    }

}
