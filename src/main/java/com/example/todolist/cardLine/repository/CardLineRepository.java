package com.example.todolist.cardLine.repository;

import com.example.todolist.cardLine.entity.CardLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardLineRepository extends JpaRepository<CardLine, Long> {
}
