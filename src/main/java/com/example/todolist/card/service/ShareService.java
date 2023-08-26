package com.example.todolist.card.service;

import com.example.todolist.card.dto.CardResponseDto;
import com.example.todolist.card.entity.Card;
import com.example.todolist.card.entity.Share;
import com.example.todolist.card.mapper.CardMapper;
import com.example.todolist.card.repository.CardRepository;
import com.example.todolist.card.repository.ShareRepository;
import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.exception.ErrorCode;
import com.example.todolist.member.entity.Member;
import com.example.todolist.member.repository.FriendRepository;
import com.example.todolist.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShareService {

    private final ShareRepository shareRepository;
    private final CardRepository cardRepository;
    private final FriendRepository friendRepository;

    @Transactional
    public void addShare(Member member, Long cardNo, List<Long> friends){
        Card card = cardRepository.findById(cardNo).orElseThrow(
                ()->new CustomException(ErrorCode.CARD_READ_FAILED)
        );

        if(!card.getMember().getMemberNo().equals(member.getMemberNo()))
            throw new CustomException(ErrorCode.AUTH_FAIL);
        
        friends.forEach(friend -> {
            boolean isShared = shareRepository.findAllByMember(member).stream().anyMatch(
                    share -> share.getToShare().equals(friend)
            );
            if(isShared) throw new CustomException(ErrorCode.CARD_SHARE_AREADY);
            shareRepository.save(Share.builder()
                    .member(member)
                    .toShare(friend)
                    .done(false)
                    .card(card).build());
        });
    }

    @Transactional
    public void deleteShare(Member member, Long sharedNo) {
        Share share = shareRepository.findById(sharedNo).orElseThrow(
                ()->new CustomException(ErrorCode.CARD_READ_FAILED)
        );
        if(!share.getToShare().equals(member.getMemberNo())
                && !share.getCard().getMember().getMemberNo().equals(member.getMemberNo())){
          throw new CustomException(ErrorCode.AUTH_FAIL);
        }
        shareRepository.deleteById(sharedNo);
    }

    @Transactional(readOnly = true)
    public List<CardResponseDto> getShareFromMe(UserDetailsImpl userDetails) {
        List<Share> shareList = shareRepository.findAllByMember(userDetails.getMember());
        List<Card> cardList = shareList.stream().map(Share::getCard).collect(Collectors.toList());
        return cardList.stream().map(CardMapper.INSTANCE::cardToResponseDto).collect(Collectors.toList());
    }
}
