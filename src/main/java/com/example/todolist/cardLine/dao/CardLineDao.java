package com.example.todolist.cardLine.dao;

import com.example.todolist.cardLine.dto.CardLineDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@Deprecated
public interface CardLineDao {

    List<CardLineDto> listCardLine(Long[] cardNoArr);

}
