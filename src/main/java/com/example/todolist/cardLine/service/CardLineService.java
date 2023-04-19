package com.example.todolist.cardLine.service;

import com.example.todolist.cardLine.dao.CardLineDao;
import com.example.todolist.cardLine.dto.CardLineDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardLineService {

    private final CardLineDao cardLineDao;

    public List<CardLineDto> cardLineList(Long[] cardNoArr) {
        System.out.println(cardLineDao.listCardLine(cardNoArr).size());
        return cardLineDao.listCardLine(cardNoArr);
    };
}
