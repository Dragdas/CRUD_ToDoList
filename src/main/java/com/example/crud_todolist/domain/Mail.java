package com.example.crud_todolist.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Mail {
    private final String mailTo;
    private final String toCC;
    private final String subject;
    private final String message;
}
