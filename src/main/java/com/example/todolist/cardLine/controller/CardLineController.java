package com.example.todolist.cardLine.controller;

import com.example.todolist.cardLine.dto.CardLineDto;
import com.example.todolist.cardLine.service.CardLineService;
import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CardLineController {

    private final CardLineService cardLineService;

    @PostMapping(value = "/cardLine")
    public ResponseMessage<String> createCard(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                              @RequestBody CardLineDto cardLineDto) {

        cardLineService.createCardLine(userDetails.getMember(), cardLineDto);
        return new ResponseMessage(MessageCode.CARDLINE_CREATE_SUCCESS, null);
    }

    @PutMapping(value = "/cardLine/{cardLineNo}")
    public ResponseMessage<String> checkCardLine(@PathVariable Long cardLineNo) {

        cardLineService.checkCardLine(cardLineNo);
        return new ResponseMessage(MessageCode.SUCCESS, null);
    }

}
