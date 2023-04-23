package com.example.todolist.cardLine.controller;

import com.example.todolist.cardLine.service.CardLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CardLineController {

    private final CardLineService cardLineService;

}
