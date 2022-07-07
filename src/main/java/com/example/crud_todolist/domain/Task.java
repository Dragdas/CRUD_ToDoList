package com.example.crud_todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String content;
}
