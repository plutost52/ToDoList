package com.example.todolist.member.service;

import com.example.todolist.common.MessageCode;
import com.example.todolist.common.ResponseMessage;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.common.type.ConnectType;
import com.example.todolist.member.dto.MemberRequestDto;
import com.example.todolist.member.dto.MemberResponseDto;
import com.example.todolist.member.entity.ConnectLog;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.mapper.MemberMapper;
import com.example.todolist.member.repository.ConnectLogRepository;
import com.example.todolist.member.repository.MemberRepository;
import com.example.todolist.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final ConnectLogRepository connectLogRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public void createMember(MemberRequestDto memberRequestDto) {

        Member member = memberRepository.findByMemberEmail(memberRequestDto.getMemberEmail());
        if(member != null) throw new CustomException(ErrorCode.EMAIL_DUPLICATED);

        member = memberRepository.findByMemberNickname(memberRequestDto.getMemberNickname());

        if(member != null) throw new CustomException(ErrorCode.NICKNAME_DUPLICATED);
        String encodingPwd = passwordEncoder.encode(memberRequestDto.getMemberPwd());

        member = Member.builder()
                .memberEmail(memberRequestDto.getMemberEmail())
                .memberPwd(encodingPwd)
                .memberName(memberRequestDto.getMemberName())
                .memberNickname(memberRequestDto.getMemberNickname())
                .build();
        memberRepository.save(member);
    }

    @Transactional
    public ResponseMessage login(MemberRequestDto memberRequestDto, HttpServletResponse response) throws CustomException{

        Member member = memberRepository.findByMemberEmail(memberRequestDto.getMemberEmail());

        if(member == null) throw new CustomException(ErrorCode.NOT_FOUND_EMAIL);

        if(member.getLockedAt()!=null && member.getLockedAt().plusMinutes(5).isAfter(LocalDateTime.now())) {
            return new ResponseMessage(ErrorCode.LOCKED_MEMBER, member.getLockedAt());
        }else if(member.getLockedAt()!=null && member.getLockedAt().plusMinutes(5).isBefore(LocalDateTime.now())){
            member.logInTryCountUpdate(true);
            member.unLockMember();
        }

        if(!passwordEncoder.matches(memberRequestDto.getMemberPwd(), member.getMemberPwd())) {
            member.logInTryCountUpdate(false);
            log.info("login with incorrect password - try count {}", member.getLogInTryCount());
            int loginTryCount = member.getLogInTryCount();
            if(loginTryCount >= 5) {
                member.lockMember();
                return new ResponseMessage(ErrorCode.LOCKED_MEMBER, member.getLockedAt());
            }
            return new ResponseMessage<>(ErrorCode.INCORRECT_PASSWORD, member.getLogInTryCount());
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(member.getMemberEmail()));
        member.logInTryCountUpdate(true);

        connectLogRepository.save(
                ConnectLog.builder()
                        .type(ConnectType.LOGIN.name())
                        .member(member)
                        .build()
        );

        return new ResponseMessage<>(MessageCode.SUCCESS, MemberMapper.INSTANCE.memberToResponseDto(member));
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
        Member member = memberRepository.findByMemberEmail(memberRequestDto.getMemberEmail());
        if(member != null) throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
    }

    @Transactional(readOnly = true)
    public void checkNickname(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findByMemberNickname(memberRequestDto.getMemberNickname());
        if(member != null) throw new CustomException(ErrorCode.NICKNAME_DUPLICATED);
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findByString(String search) {

        if("".equals(search) || search == null) throw new CustomException(ErrorCode.SEARCH_KEYWORD_NOT_BLACK);
        List<Member> members = memberRepository.findByMemberNicknameContaining(search);

        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for (Member member :members) {
            memberResponseDtoList.add(MemberMapper.INSTANCE.memberToResponseDto(member));
        }
        return memberResponseDtoList;
    }
}
