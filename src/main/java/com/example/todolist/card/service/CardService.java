package com.example.todolist.card.service;

import com.example.todolist.card.dao.CardDao;
import com.example.todolist.card.dto.CardDto;
import com.example.todolist.card.dto.CardRequestDto;
import com.example.todolist.card.entity.Card;
import com.example.todolist.card.repository.CardRepository;
import com.example.todolist.cardLine.dao.CardLineDao;
import com.example.todolist.cardLine.dto.CardLineDto;
import com.example.todolist.cardLine.repository.CardLineRepository;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardDao cardDao;
    private final CardLineDao cardLineDao;

    private final CardRepository cardRepository;
    private final CardLineRepository cardLineRepository;

    public void createCard(Member member) {
        cardRepository.save(Card.builder()
                .cardTitle("")
                .cardDone(false)
                .member(member)
                .build());
    };

    public void deleteCard(CardRequestDto requestBody) {

        Long[] cardNoArr = requestBody.getCardNo();

        if (cardNoArr == null || cardNoArr.length < 0)
            throw new CustomException(ErrorCode.CARD_DELETE_BADREQUEST);
        else {

            Long result = cardDao.deleteCard(cardNoArr);
            if (result == 0)
                throw new CustomException(ErrorCode.CARD_CREATE_FAILED);

        }

    }

    public CardDto readCard(Long cardNo) {

        if (cardNo <= 0L)
            throw new CustomException(ErrorCode.CARD_READ_BADREQUEST);

        CardDto result = cardDao.readCard(cardNo);
        if (result == null)
            throw new CustomException(ErrorCode.CARD_READ_FAILED);
        result.setCardLine(cardLineDao.listCardLine(new Long[] { result.getCardNo() }));
        return result;
    };

    public List<CardDto> listCard(Member member, HttpServletRequest request) {

        List<CardDto> result = new ArrayList<CardDto>();
        Long memberNo = member.getMemberNo();
        String type = request.getRequestURI().contains("shared") ? "shared" : "my";

        if (memberNo <= 0)
            throw new CustomException(ErrorCode.CARD_LIST_BADREQUEST);

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

        } else {

        }

        return result;
    };

    public void updateCardTitle(Long cardNo, CardRequestDto cardRequest) {

        String cardTitle = cardRequest.getCardTitle();

        if (cardNo <= 0 || cardTitle == "")
            throw new CustomException(ErrorCode.CARD_UPDATE_TITLE_BADREQUEST);
        Long result = cardDao.updateCardTitle(cardNo, cardTitle);
        System.out.println(result);

        if (result == 0)
            throw new CustomException(ErrorCode.CARD_UPDATE_TITLE_FAILED);

    }

    public void updateCardDone(CardRequestDto cardRequest) {

        Boolean cardDone = cardRequest.getCardDone();
        Long[] cardNoArr = cardRequest.getCardNo();

        if (cardDone == null || cardNoArr == null || cardNoArr.length <= 0)
            throw new CustomException(ErrorCode.CARD_UPDATE_DONE_BADREQUEST);

        Long result = cardDao.updateCardDone(cardDone, cardNoArr);

        if (result == 0)
            throw new CustomException(ErrorCode.CARD_UPDATE_DONE_FAILED);

    }

}
