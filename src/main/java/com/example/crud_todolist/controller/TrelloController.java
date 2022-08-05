package com.example.crud_todolist.controller;

import com.example.crud_todolist.domain.CreatedTrelloCard;
import com.example.crud_todolist.domain.TrelloBoardDto;
import com.example.crud_todolist.domain.TrelloCardDto;
import com.example.crud_todolist.service.TrelloService;
import com.example.crud_todolist.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloService trelloService;

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards() {

        return ResponseEntity.ok(trelloService.fetchTrelloBoards());

    }

    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCard> createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return ResponseEntity.ok(trelloService.createTrelloCard(trelloCardDto));

    }

}
