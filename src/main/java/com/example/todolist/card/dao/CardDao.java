package com.example.todolist.card.dao;

import com.example.todolist.card.dto.CardDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.ResultSet;
import java.util.List;
@Deprecated
@Mapper
public interface CardDao {

    Long createCard (Long memberNo);

    Long deleteCard (Long[] cardNoArr);

    CardDto readCard (Long cardNo);

    List<CardDto> listCard(Long memberNo);

    Long updateCardTitle (Long cardNo, String cardTitle);

    Long updateCardDone (Boolean cardDone, Long[] cardNoArr);

}
