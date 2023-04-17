package com.example.todolist.card.controller;

import com.example.todolist.card.dto.CardDto;
import com.example.todolist.card.service.CardService;
import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping(value = "/card", produces="application/json; charset=utf8")
    public ResponseMessage<String> createCard(@RequestBody CardDto cardDto) throws Exception {

        int result = cardService.createCard(cardDto);
        System.out.println("create Result : " + result);
        return new ResponseMessage(MessageCode.SUCCESS, null);
    }

}
