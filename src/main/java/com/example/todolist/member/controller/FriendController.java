package com.example.todolist.member.controller;


import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.member.dto.FriendResponseDto;
import com.example.todolist.member.service.FriendService;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/friend")
    public  ResponseMessage<List<FriendResponseDto>> deleteFriend(@AuthenticationPrincipal UserDetailsImpl userDetails){

        return new ResponseMessage<>(MessageCode.SUCCESS, friendService.getFreinds(userDetails));
    }

}
