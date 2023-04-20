package com.example.todolist.member.dao;

import com.example.todolist.member.dto.MemberDto;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    void createMember(MemberRequestDto memberRequestDto);

    MemberDto findByEmail(String email);

    MemberDto findByNickname(String memberNickname);

    MemberResponseDto login(MemberRequestDto memberRequestDto);
}
