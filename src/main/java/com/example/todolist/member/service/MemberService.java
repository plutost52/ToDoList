package com.example.todolist.member.service;

import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.dao.MemberDao;
import com.example.todolist.member.dto.MemberDto;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void createMember(MemberRequestDto memberRequestDto) {

        MemberDto memberDto = memberDao.findByEmail(memberRequestDto.getMemberEmail());
        if(memberDto != null) throw new CustomException(ErrorCode.EMAIL_DUPLICATED);

        memberDto = memberDao.findByNickname(memberRequestDto.getMemberNickname());
        if(memberDto != null) throw new CustomException(ErrorCode.NICKNAME_DUPLICATED);

        String encodingPwd = passwordEncoder.encode(memberRequestDto.getMemberPwd());
        memberRequestDto.updatePwdEncoding(encodingPwd);

        memberDao.createMember(memberRequestDto);
    }


    public void login(MemberRequestDto memberRequestDto, HttpServletResponse response) {

        MemberDto memberDto = memberDao.findByEmail(memberRequestDto.getMemberEmail());
        if(memberDto == null) throw new CustomException(ErrorCode.NOT_FOUND_EMAIL);

        if(!passwordEncoder.matches(memberRequestDto.getMemberPwd(), memberDto.getMemberPwd())) {
            throw new CustomException(ErrorCode.INCORRECT_PASSWORD);
        }
        log.info("pwd checking success");
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(memberDto.getMemberEmail()));
        log.info("header checking success");
    }
}
