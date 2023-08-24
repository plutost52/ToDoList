package com.example.todolist.card.repository;

import com.example.todolist.card.entity.Share;
import com.example.todolist.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findAllByMember(Member member);
}
