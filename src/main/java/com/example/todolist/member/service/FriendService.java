package com.example.todolist.member.service;


import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.dto.FriendResponseDto;
import com.example.todolist.member.entity.Friend;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.mapper.MemberMapper;
import com.example.todolist.member.repository.FriendRepository;
import com.example.todolist.member.repository.MemberRepository;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    public void addFriend(UserDetailsImpl userDetails, Long toFriendNo){
        if(userDetails.getMember().getMemberNo().equals(toFriendNo))
            throw new CustomException(ErrorCode.BAD_REQUEST_ERROR);

        memberRepository.findById(toFriendNo).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        List<Friend> friends = friendRepository.findAllByMemberAndAndToFriend(userDetails.getMember(), toFriendNo);

        if(friends.size() > 0)  throw new CustomException(ErrorCode.FRIEND_DUPLICATED);

        friendRepository.save(
                Friend.builder()
                        .member(userDetails.getMember())
                        .toFriend(toFriendNo)
                        .build()
        );
    }

    public void deleteFriend(UserDetailsImpl userDetails, Long toFriendNo){

        List<Friend> friends = friendRepository.findAllByMemberAndAndToFriend(userDetails.getMember(), toFriendNo);

        if(friends.size() == 0)  throw new CustomException(ErrorCode.FRIEND_NOT_FOUND);

        friendRepository.deleteById(friends.get(0).getId());
    }

    public List<FriendResponseDto> getFreinds(UserDetailsImpl userDetails) {

        List<Friend> friends = friendRepository.findAllByMember(userDetails.getMember());
        List<Member> members = memberRepository.findByMemberNoIn(friends.stream()
                .map(Friend::getToFriend).collect(Collectors.toList()));

        return MemberMapper.INSTANCE.memberToFriendResponseDto(members);
    }
}
