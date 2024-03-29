package com.example.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToDoListBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListBeApplication.class, args);
    }

}
