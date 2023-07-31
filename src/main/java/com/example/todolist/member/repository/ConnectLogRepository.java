package com.example.todolist.member.repository;

import com.example.todolist.member.entity.ConnectLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectLogRepository extends JpaRepository<ConnectLog, Long> {

}
