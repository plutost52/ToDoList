package com.example.todolist.member.controller;

import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.member.dto.MemberDto;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.dto.MemberSearchDto;
import com.example.todolist.member.service.MemberService;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member")
    public ResponseMessage<?> createMember(@RequestBody @Valid MemberRequestDto memberRequestDto){
        memberService.createMember(memberRequestDto);
        return new ResponseMessage<>(MessageCode.SUCCESS_SIGN_UP, null);
    }

    @PostMapping(value = "/member/login")
    public ResponseMessage<?> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) throws CustomException{

        return memberService.login(memberRequestDto, response);
    }

    @PostMapping(value = "/member/logOut")
    public ResponseMessage<?> logOut(@AuthenticationPrincipal UserDetailsImpl userDetails) throws CustomException{
        memberService.logOut(userDetails.getMember());
        return new ResponseMessage<>(MessageCode.SUCCESS_MEMBER_LOGOUT, null);
    }

    @PatchMapping(value = "/member")
    public ResponseMessage<?> updateMember(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @RequestBody MemberRequestDto memberRequestDto){
        memberService.update(userDetails.getMember(), memberRequestDto);
        return new ResponseMessage<>(MessageCode.SUCCESS_MEMBER_UPDATE, null);
    }

    @GetMapping(value = "/member/email")
    public ResponseMessage<?> checkEmail(@RequestBody MemberRequestDto memberRequestDto){
        memberService.checkEmail(memberRequestDto);
        return new ResponseMessage<>(MessageCode.CHECK_EMAIL, null);
    }

    @GetMapping(value = "/member/nickname")
    public ResponseMessage<?> checkNickname(@RequestBody MemberRequestDto memberRequestDto){
        memberService.checkNickname(memberRequestDto);
        return new ResponseMessage<>(MessageCode.CHECK_NICKNAME, null);
    }

    @GetMapping(value = "/member")
    public ResponseMessage<List<MemberResponseDto>> searchMember(@RequestParam String search){
        List<MemberResponseDto> memberResponseDtos = memberService.findByString(search);
        return new ResponseMessage<>(MessageCode.SUCCESS, memberResponseDtos);
    }

}
