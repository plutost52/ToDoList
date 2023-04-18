package com.example.todolist.card.service;

import com.example.todolist.card.dao.CardDao;
import com.example.todolist.card.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardDao cardDao;

    public int createCard(CardDto cardDto) {
        int result = cardDao.createCard(cardDto);
        return result;
    };

    public int deleteCard(int[] cardNoArr) {
        int result = cardDao.deleteCard(cardNoArr);
        return result;
    }

    public List<CardDto> listCard(int memberNo, String type) {
        System.out.println("[ Service ] MemberNo : " + memberNo + " / type : " + type);

        List<CardDto> cardList = cardDao.listCard(memberNo);
        for (CardDto card : cardList) {
            System.out.println(card.toString());
        }

        return null;
    };

}
