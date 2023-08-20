package com.example.todolist.member.repository;

import com.example.todolist.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberEmail(String memberEmail);

    Member findByMemberNickname(String memberNickname);

    List<Member> findByMemberNicknameContaining(String search);
    List<Member> findByMemberNoIn(List<Long> members);
}
