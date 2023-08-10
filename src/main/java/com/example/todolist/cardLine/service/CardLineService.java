package com.example.todolist.cardLine.service;

import com.example.todolist.card.entity.Card;
import com.example.todolist.card.repository.CardRepository;
import com.example.todolist.cardLine.dao.CardLineDao;
import com.example.todolist.cardLine.dto.CardLineDto;
import com.example.todolist.cardLine.entity.CardLine;
import com.example.todolist.cardLine.repository.CardLineRepository;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardLineService {

    private final CardLineDao cardLineDao;
    private final CardRepository cardRepository;
    private final CardLineRepository cardLineRepository;

    public List<CardLineDto> cardLineList(Long[] cardNoArr) {
        System.out.println(cardLineDao.listCardLine(cardNoArr).size());
        return cardLineDao.listCardLine(cardNoArr);
    };

    @Transactional
    public void createCardLine(Member member, CardLineDto cardLineDto) {
        Card card = cardRepository.findById(cardLineDto.getCardNo()).orElseThrow(
                () -> new CustomException(ErrorCode.CARD_READ_FAILED)
        );

        if(!card.getMember().getMemberNo().equals(member.getMemberNo()))
            throw new CustomException(ErrorCode.AUTH_FAIL);

        cardLineRepository.save(CardLine.builder()
                        .lineNo(card.getCardLine().size() + 1L)
                        .cardLineValue(cardLineDto.getCardLineValue())
                        .cardLineChecked(false)
                        .card(card)
                        .build());
    }

    @Transactional
    public void checkCardLine(Long cardLineNo) {

        CardLine cardLine = cardLineRepository.findById(cardLineNo).orElseThrow(
                () -> new CustomException(ErrorCode.CARD_READ_BADREQUEST)
        );

        cardLine.checkCardLine();
    }
}
