package com.example.crud_todolist.service;


import com.example.crud_todolist.config.AdminConfig;
import com.example.crud_todolist.domain.CreatedTrelloCardDto;
import com.example.crud_todolist.domain.Mail;
import com.example.crud_todolist.domain.TrelloBoardDto;
import com.example.crud_todolist.domain.TrelloCardDto;
import com.example.crud_todolist.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrelloService {

    private final TrelloClient trelloClient;
    private final SimpleEmailService simpleEmailService;
    private final AdminConfig adminConfig;
    public static final String SUBJECT = "TASKS: New trello card";

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoardsWithLists();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        Optional.ofNullable(newCard).ifPresent(card -> {
            simpleEmailService.send(Mail.builder()
                    .mailTo(adminConfig.getAdminMail())
                    .subject(SUBJECT)
                    .message("New card:" + trelloCardDto.getName() + " has been created on your Trello account")
                    .build());
        });

        return newCard;
    }

}
