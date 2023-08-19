package com.example.todolist.member.repository;

import com.example.todolist.member.entity.Friend;
import com.example.todolist.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByMember(Member member);
}
