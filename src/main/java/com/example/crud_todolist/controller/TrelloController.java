package com.example.crud_todolist.controller;

import com.example.crud_todolist.domain.CreatedTrelloCard;
import com.example.crud_todolist.domain.TrelloBoardDto;
import com.example.crud_todolist.domain.TrelloCardDto;
import com.example.crud_todolist.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.forEach(trelloBoardDto -> {
            String boardId = trelloBoardDto.getId();
            trelloBoardDto.setLists(trelloClient.getListsFromBoards(boardId));
        });

        trelloBoards.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {

            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
            System.out.println("Contains lists:");
            trelloBoardDto.getLists().forEach(System.out::println);

        });
    }

    @PostMapping("cards")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }



}
