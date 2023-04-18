package com.example.todolist.card.controller;

import com.example.todolist.card.dto.CardDto;
import com.example.todolist.card.service.CardService;
import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping(value = "/card", produces="application/json; charset=utf8")
    public ResponseMessage<String> createCard(@RequestBody CardDto cardDto) throws Exception {

        String message = "카드 추가 실패";

        int result = cardService.createCard(cardDto);
        if (result == 1)
            message = "카드 추가 성공";
        System.out.println("create Result : " + message);

        return new ResponseMessage(MessageCode.SUCCESS, null);
    }

    @DeleteMapping(value = "/card/{cardNo}")
    public ResponseMessage<String> deleteCard(@PathVariable("cardNo") String noArr) throws Exception {

        String message = "카드 삭제 실패";

        if (noArr.isEmpty()) {
            message += " : 카드 정보 없음";
        } else {

            int[] cardNoArr = Arrays.stream(noArr.split(",")).mapToInt(Integer::parseInt).toArray();
            System.out.println("card List : " + Arrays.toString(cardNoArr));

            int result = cardService.deleteCard(cardNoArr);
            if (result == 1)
                message = "카드 삭제 성공";
        }

        return new ResponseMessage<>(MessageCode.SUCCESS, null);
    }

    @GetMapping(value = { "/card", "card/shared" })
    public ResponseMessage<String> listCard(HttpServletRequest request) throws Exception {

        String uri = request.getRequestURI();
        String type = "my";
        if (uri.contains("shared"))
            type = "shared";

        int memberNo = 1;
        String message = "카드 목록 조회 실패";

        if (memberNo < 1) {
            message += " : 사용자 정보 없음";
        } else {

            List<CardDto> cardList = cardService.listCard(memberNo, type);
            message = "카드 목록 조회 성공";

        }

        return new ResponseMessage<>(message, 200, null);
    }

}
