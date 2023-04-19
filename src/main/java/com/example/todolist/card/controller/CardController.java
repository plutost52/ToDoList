package com.example.todolist.card.controller;

import com.example.todolist.card.dto.CardDto;
import com.example.todolist.card.service.CardService;
import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping(value = "/card")
    public ResponseMessage<String> createCard() throws Exception {

        // 임시 Member Number 설정
        Long memberNo = 1L;

        cardService.createCard(memberNo);
        return new ResponseMessage(MessageCode.CARD_CREATE_SUCCESS, null);
    }

    @DeleteMapping(value = "/card/{cardNo}")
    public ResponseMessage<String> deleteCard(@PathVariable("cardNo") String noArr) throws Exception {

        cardService.deleteCard(noArr);
        return new ResponseMessage(MessageCode.CARD_DELETE_SUCCESS, null);
    }

    @GetMapping(value = { "/card", "card/shared" })
    public ResponseMessage<String> listCard(HttpServletRequest request) throws Exception {

        // 임시 Member Number 설정
        Long memberNo = 1L;

        String type = request.getRequestURI().contains("shared") ? "shared" : "my";

        List<CardDto> cardList = cardService.listCard(memberNo, type);
        return new ResponseMessage(MessageCode.CARD_LIST_SUCCESS, null);
    }

}
