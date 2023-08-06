package com.example.todolist.card.mapper;

import com.example.todolist.card.dto.CardResponseDto;
import com.example.todolist.card.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    @Mapping(source = "member.memberNo", target = "memberNo")
    CardResponseDto cardToResponseDto(Card card);
}
