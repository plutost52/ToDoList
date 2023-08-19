package com.example.todolist.member.controller;


import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.member.service.FriendService;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/friend/{memberNo}")
    public ResponseMessage<?> createMember(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @PathVariable("memberNo") Long memberNo
                                           ){
        friendService.addFriend(userDetails, memberNo);
        return new ResponseMessage<>(MessageCode.SUCCESS, null);
    }

    @DeleteMapping("/friend/{memberNo}")
    public ResponseMessage<?> deleteFriend(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                           @PathVariable("memberNo") Long memberNo
    ){
        friendService.deleteFriend(userDetails, memberNo);
        return new ResponseMessage<>(MessageCode.SUCCESS, null);
    }

}