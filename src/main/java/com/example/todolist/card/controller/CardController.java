package com.example.todolist.card.controller;

import com.example.todolist.card.dto.CardDto;
import com.example.todolist.card.dto.CardRequestDto;
import com.example.todolist.card.dto.CardResponseDto;
import com.example.todolist.card.service.CardService;
import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping(value = "/card")
    public ResponseMessage<String> createCard(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        cardService.createCard(userDetails.getMember());
        return new ResponseMessage(MessageCode.CARD_CREATE_SUCCESS, null);
    }

    @DeleteMapping(value = "/card")
    public ResponseMessage<String> deleteCard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody List<Long> cardList) {
        cardService.deleteCard(userDetails.getMember(), cardList);
        return new ResponseMessage(MessageCode.CARD_DELETE_SUCCESS, null);
    }

    @GetMapping(value = "/card/{cardNo}")
    public ResponseMessage<?> readCard(@PathVariable("cardNo") Long cardNo) {

        CardResponseDto responseDto = cardService.readCard(cardNo);
        return new ResponseMessage(MessageCode.CARD_READ_SUCCESS, responseDto);
    }

    @GetMapping(value = { "/card", "card/shared" })
    public ResponseMessage<CardResponseDto> listCard(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<CardResponseDto> responseDtoList = cardService.listCard(userDetails.getMember());
        return new ResponseMessage(MessageCode.CARD_LIST_SUCCESS, responseDtoList);
    }

    @PutMapping(value = "/card/{cardNo}")
    public ResponseMessage<String> updateCardTitle(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable("cardNo") Long cardNo, @RequestBody CardRequestDto cardRequest) {

        cardService.updateCardTitle(userDetails.getMember(), cardNo, cardRequest);
        return new ResponseMessage(MessageCode.CARD_UPDATETITLE_SUCCESS, null);
    }

    @PutMapping(value = { "/card/done", "/card/shared/done" })
    public ResponseMessage<String> updateCardDone(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  HttpServletRequest request,
                                                  @RequestBody CardRequestDto cardRequest) {

        cardService.updateCardDone(userDetails.getMember(), cardRequest);
        //List<CardDto> cardList = cardService.listCard(userDetails.getMember(), request);
        return new ResponseMessage(MessageCode.CARD_UPDATEDONE_SUCCESS, null);
    }

}
