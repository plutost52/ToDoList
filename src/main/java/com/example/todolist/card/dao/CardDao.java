package com.example.todolist.card.dao;

import com.example.todolist.card.dto.CardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardDao {

    int createCard (CardDto cardDto);

    void listCard(Long no);

}
