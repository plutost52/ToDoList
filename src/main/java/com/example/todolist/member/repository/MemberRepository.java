package com.example.todolist.member.repository;

import com.example.todolist.member.entity.Member;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberEmail(String memberEmail);

    Member findByMemberNickname(String memberNickname);
}
