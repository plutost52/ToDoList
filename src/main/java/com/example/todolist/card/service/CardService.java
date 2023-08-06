package com.example.todolist.card.service;

import com.example.todolist.card.dao.CardDao;
import com.example.todolist.card.dto.CardDto;
import com.example.todolist.card.dto.CardRequestDto;
import com.example.todolist.card.dto.CardResponseDto;
import com.example.todolist.card.entity.Card;
import com.example.todolist.card.mapper.CardMapper;
import com.example.todolist.card.repository.CardRepository;
import com.example.todolist.cardLine.dao.CardLineDao;
import com.example.todolist.cardLine.dto.CardLineDto;
import com.example.todolist.cardLine.repository.CardLineRepository;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.example.todolist.common.exception.ErrorCode.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardService {

    private final CardDao cardDao;
    private final CardLineDao cardLineDao;

    private final CardRepository cardRepository;
    private final CardLineRepository cardLineRepository;

    @Transactional
    public void createCard(Member member) {
        cardRepository.save(Card.builder()
                .cardTitle("")
                .cardDone(false)
                .member(member)
                .build());
    };

    @Transactional
    public void deleteCard(Member member, List<Long> cardList) {

        List<Card> cards = cardRepository.findAllByCardNoIn(cardList);

        if(cards.size() != cardList.size()) throw new CustomException(CARD_DELETE_BADREQUEST);

        for(Card card : cards){
            checkAuth(card, member);
        }

        cardRepository.deleteAllByCardNoIn(cardList);
    }

    @Transactional(readOnly = true)
    public CardResponseDto readCard(Long cardNo) {

        Card card = cardRepository.findById(cardNo).orElseThrow(
                () -> new CustomException(CARD_READ_BADREQUEST)
        );

        return CardMapper.INSTANCE.cardToResponseDto(card);
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

    @Transactional
    public void updateCardTitle(Member member, Long cardNo, CardRequestDto cardRequest) {

        String cardTitle = cardRequest.getCardTitle();

        if ("".equals(cardTitle)) throw new CustomException(ErrorCode.CARD_UPDATE_TITLE_BADREQUEST);

        Card card = cardRepository.findById(cardNo).orElseThrow(
                () -> new CustomException(CARD_READ_BADREQUEST)
        );

        checkAuth(card, member);

        card.updateCardTitle(cardTitle);
    }

    @Transactional
    public void updateCardDone(Member member, CardRequestDto cardRequest) {

        List<Card> cards = cardRepository.findAllByCardNoIn(cardRequest.getCardNo());

        for(Card card : cards) {
            checkAuth(card, member);
            card.updateCardDone(cardRequest.getCardDone());
        }

    }

    private void checkAuth(Card card, Member member){
        if(!card.getMember().getMemberNo().equals(member.getMemberNo())){
            throw new CustomException(AUTH_FAIL);
        }
    }

}
