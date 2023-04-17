package com.example.todolist.card.service;

import com.example.todolist.card.dao.CardDao;
import com.example.todolist.card.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardDao cardDao;

    public int createCard(CardDto cardDto) {
        int result = cardDao.createCard(cardDto);
        return result;
    };

    public List<CardDto> listCard(Long id) {

        return null;

    };

}
