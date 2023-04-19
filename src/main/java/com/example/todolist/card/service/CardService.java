package com.example.todolist.card.service;

import com.example.todolist.card.dao.CardDao;
import com.example.todolist.card.dto.CardDto;
import com.example.todolist.cardLine.dao.CardLineDao;
import com.example.todolist.cardLine.dto.CardLineDto;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardDao cardDao;
    private final CardLineDao cardLineDao;

    public void createCard(Long memberNo) {
        Long result = cardDao.createCard(memberNo);
        if (result == 0)
            throw new CustomException(ErrorCode.CARD_CREATE_FAILED);
    };

    public void deleteCard(String noArr) {

        if (noArr.length() < 0)
            throw new CustomException(ErrorCode.CARD_DELETE_BADREQUEST);
        else {

            String[] strArr = noArr.split(",");
            Long[] cardNoArr = new Long[strArr.length];
            for (Long i = 0L; i < strArr.length; i++)
                cardNoArr[i.intValue()] = Long.parseLong(strArr[i.intValue()]);
            System.out.println("card List : " + Arrays.toString(cardNoArr));

            Long result = cardDao.deleteCard(cardNoArr);
            if (result == 0)
                throw new CustomException(ErrorCode.CARD_CREATE_FAILED);

        }

    }

    public List<CardDto> listCard(Long memberNo, String type) {

        System.out.println("[ Service ] MemberNo : " + memberNo + " / type : " + type);

        List<CardDto> result = new ArrayList<CardDto>();

        if (type.equals("my")) {

            List<CardDto> cardList = cardDao.listCard(memberNo);
            Long[] cardNoArr = new Long[cardList.size()];

            for (Long i = 0L; i.intValue() < cardList.size(); i = i + 1L) {
                CardDto card = cardList.get(i.intValue());
                cardNoArr[i.intValue()] = card.getCardNo();
            }

            List<CardLineDto> cardLineList = cardLineDao.listCardLine(cardNoArr);

            for (Long i = 0L; i.intValue() < cardList.size(); i++) {

                CardDto card = cardList.get(i.intValue());
                List<CardLineDto> cardLines = new ArrayList<CardLineDto>();

                for (Long j = 0L; j.intValue() < cardLineList.size(); j++) {

                    CardLineDto cardLine = cardLineList.get(j.intValue());
                    if (cardLine.getCardNo() == card.getCardNo())
                        cardLines.add(cardLine);
                }

                card.setCardLine(cardLines);
                cardList.set(i.intValue(), card);
            }

            result = cardList;

            for (CardDto card : result) {
                System.out.println(card.toString());
            }

        } else {

        }

        return result;
    };

}
