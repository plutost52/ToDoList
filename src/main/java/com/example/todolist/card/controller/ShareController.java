package com.example.todolist.card.controller;

import com.example.todolist.card.service.ShareService;
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
    public void addShare(@AuthenticationPrincipal UserDetailsImpl userDetails,
                         @PathVariable("cardNo") Long cardNo,
                         @RequestBody List<Long> friends){
        shareService.addShare(userDetails.getMember(), cardNo, friends);
    }

    @DeleteMapping("/share/{sharedNo}")
    public void deleteShare(@AuthenticationPrincipal UserDetailsImpl userDetails,
                         @PathVariable("sharedNo") Long sharedNo){
        shareService.deleteShare(userDetails.getMember(), sharedNo);
    }
}
