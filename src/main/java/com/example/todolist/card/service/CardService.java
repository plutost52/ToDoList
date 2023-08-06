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

    @Transactional(readOnly = true)
    public List<CardResponseDto> listCard(Member member) {
        List<Card> cards = cardRepository.findAllByMember(member);
        List<CardResponseDto> responseDtoList = new ArrayList<>();
        for(Card card : cards) {
            responseDtoList.add(CardMapper.INSTANCE.cardToResponseDto(card));
        }
        return responseDtoList;
    }

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
