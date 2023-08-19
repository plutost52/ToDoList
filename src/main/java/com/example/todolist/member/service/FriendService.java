package com.example.todolist.member.service;


import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.entity.Friend;
import com.example.todolist.member.repository.FriendRepository;
import com.example.todolist.member.repository.MemberRepository;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;

    public void addFriend(UserDetailsImpl userDetails, Long toFriendNo){
        memberRepository.findById(toFriendNo).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        List<Friend> friends = friendRepository.findAllByMember(userDetails.getMember());
        if(friends.stream().anyMatch(friend -> friend.getToFriend().equals(toFriendNo))){
            throw new CustomException(ErrorCode.FRIEND_DUPLICATED);
        }

        friendRepository.save(
                Friend.builder()
                        .member(userDetails.getMember())
                        .toFriend(toFriendNo)
                        .build()
        );
    }

}
