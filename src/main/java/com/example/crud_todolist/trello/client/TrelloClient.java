package com.example.crud_todolist.trello.client;

import com.example.crud_todolist.domain.CreatedTrelloCard;
import com.example.crud_todolist.domain.TrelloBoardDto;
import com.example.crud_todolist.domain.TrelloCardDto;
import com.example.crud_todolist.domain.TrelloListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String username;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = generateGetBoardsRequestPath();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public List<TrelloListDto> getListsFromBoards(String boardId){

        URI url = generateGetListsRequestPatch(boardId);

        TrelloListDto[] listsResponse = restTemplate.getForObject(url, TrelloListDto[].class);

        return Optional.ofNullable(listsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());

    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }




    private URI generateGetBoardsRequestPath(){

        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + username + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .build()
                .encode()
                .toUri();
    }

    private URI generateGetListsRequestPatch(String boardId){

        return UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/boards/" + boardId + "/lists")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .build()
                .encode()
                .toUri();

    }

}