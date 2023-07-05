package com.example.todolist.member.dao;

import com.example.todolist.member.dto.MemberDto;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.dto.MemberSearchDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDao {
    void createMember(MemberRequestDto memberRequestDto);

    MemberDto findByEmail(String email);

    MemberDto findByNickname(String memberNickname);

    MemberResponseDto login(MemberRequestDto memberRequestDto);

    MemberDto findById(Long memberNo);

    void updateMember(MemberRequestDto memberRequestDto);

    List<MemberResponseDto> findByString(MemberSearchDto memberSearchDto);
}
