package com.example.todolist.card.repository;

import com.example.todolist.card.entity.Card;
import com.example.todolist.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByCardNoIn(List<Long> cardId);
    void deleteAllByCardNoIn(List<Long> cardList);
    List<Card> findAllByMember(Member member);
}
