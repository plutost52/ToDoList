package com.example.todolist.card.dao;

import com.example.todolist.card.dto.CardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.ResultSet;
import java.util.List;

@Mapper
public interface CardDao {

    Long createCard (Long memberNo);

    Long deleteCard (Long[] cardNoArr);

    List<CardDto> listCard(Long memberNo);

}
