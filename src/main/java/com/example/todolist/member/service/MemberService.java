package com.example.todolist.member.service;

import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.dao.MemberDao;
import com.example.todolist.member.dto.MemberDto;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.dto.MemberSearchDto;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.mapper.MemberMapper;
import com.example.todolist.member.repository.MemberRepository;
import com.example.todolist.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberDao memberDao;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void createMember(MemberRequestDto memberRequestDto) {

        Member member = memberRepository.findByMemberEmail(memberRequestDto.getMemberEmail());
        if(member != null) throw new CustomException(ErrorCode.EMAIL_DUPLICATED);

        member = memberRepository.findByMemberNickname(memberRequestDto.getMemberNickname());

        if(member != null) throw new CustomException(ErrorCode.NICKNAME_DUPLICATED);

        String encodingPwd = passwordEncoder.encode(memberRequestDto.getMemberPwd());

        /**
         *  It will be deprecated
        memberRequestDto.updatePwdEncoding(encodingPwd);
        memberDao.createMember(memberRequestDto);
        */

        member = Member.builder()
                .memberEmail(memberRequestDto.getMemberEmail())
                .memberPwd(encodingPwd)
                .memberName(memberRequestDto.getMemberName())
                .memberNickname(memberRequestDto.getMemberNickname())
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public MemberResponseDto login(MemberRequestDto memberRequestDto, HttpServletResponse response) {

        Member member = memberRepository.findByMemberEmail(memberRequestDto.getMemberEmail());
        if(member == null) throw new CustomException(ErrorCode.NOT_FOUND_EMAIL);

        if(!passwordEncoder.matches(memberRequestDto.getMemberPwd(), member.getMemberPwd())) {
            throw new CustomException(ErrorCode.INCORRECT_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getMemberEmail()));
        MemberResponseDto responseDto = MemberMapper.INSTANCE.memberToResponseDto(member);

        return MemberMapper.INSTANCE.memberToResponseDto(member);

    }

    @Transactional
    public void update(Member member, MemberRequestDto memberRequestDto) {

        Member isNicknameDuplicated = memberRepository.findByMemberNickname(memberRequestDto.getMemberNickname());
        if(isNicknameDuplicated != null) throw new CustomException(ErrorCode.NICKNAME_DUPLICATED);

        if(memberRequestDto.getMemberPwd() != null) {
            String encodingPwd = passwordEncoder.encode(memberRequestDto.getMemberPwd());
            memberRequestDto.updatePwdEncoding(encodingPwd);
        }

        Member updateMember = memberRepository.findById(member.getMemberNo()).get();
        updateMember.update(memberRequestDto.getMemberPwd(), memberRequestDto.getMemberNickname());

    }

    @Transactional(readOnly = true)
    public void checkEmail(MemberRequestDto memberRequestDto) {
        MemberDto memberDto = memberDao.findByEmail(memberRequestDto.getMemberEmail());
        if(memberDto != null) throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
    }

    @Transactional(readOnly = true)
    public void checkNickname(MemberRequestDto memberRequestDto) {
        MemberDto memberDto = memberDao.findByNickname(memberRequestDto.getMemberNickname());
        if(memberDto != null) throw new CustomException(ErrorCode.NICKNAME_DUPLICATED);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findByString(MemberSearchDto memberSearchDto) {
        System.out.println("input search : " + memberSearchDto.getSearch());
        List<MemberResponseDto> memberResponseDtos = memberDao.findByString(memberSearchDto);
        for (MemberResponseDto m:memberResponseDtos) {
            System.out.println(m.getMemberNo() + " ::: " + m.getMemberNickname());
        }
        return memberResponseDtos;
    }
}
