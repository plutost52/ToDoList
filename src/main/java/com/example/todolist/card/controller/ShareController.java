package com.example.todolist.card.controller;

import com.example.todolist.card.dto.CardResponseDto;
import com.example.todolist.card.service.ShareService;
import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ShareController {

    private final ShareService shareService;

    @PostMapping("/share/{cardNo}")
    public ResponseMessage<?> addShare(@AuthenticationPrincipal UserDetailsImpl userDetails,
                         @PathVariable("cardNo") Long cardNo,
                         @RequestBody List<Long> friends){
        shareService.addShare(userDetails.getMember(), cardNo, friends);
        return new ResponseMessage(MessageCode.SUCCESS, null);
    }

    @GetMapping("/share")
    public ResponseMessage<List<CardResponseDto>> getShareFromMe(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new ResponseMessage(MessageCode.CARD_LIST_SUCCESS,shareService.getShareFromMe(userDetails));
    }

    @GetMapping("/share/{memberNo}")
    public ResponseMessage<List<CardResponseDto>> getShareToMe(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long memberNo){
        return new ResponseMessage(MessageCode.CARD_LIST_SUCCESS,shareService.getShareToMe(userDetails, memberNo));
    }

    @DeleteMapping("/share/{sharedNo}")
    public ResponseMessage<?> deleteShare(@AuthenticationPrincipal UserDetailsImpl userDetails,
                         @PathVariable("sharedNo") Long sharedNo){
        shareService.deleteShare(userDetails.getMember(), sharedNo);
        return new ResponseMessage(MessageCode.SUCCESS, null);
    }
}
