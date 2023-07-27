package com.example.todolist.member.mapper;

import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberResponseDto memberToResponseDto(Member member);

}
