package com.example.todolist.card.dao;

import com.example.todolist.card.dto.CardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.ResultSet;
import java.util.List;

@Mapper
public interface CardDao {

    int createCard (CardDto cardDto);

    int deleteCard (int[] cardNoArr);

    List<CardDto> listCard(int memberNo);

}
