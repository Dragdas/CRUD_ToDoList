package com.example.crud_todolist.trello.facade;

import com.example.crud_todolist.domain.*;
import com.example.crud_todolist.mapper.TrelloMapper;
import com.example.crud_todolist.service.TrelloService;
import com.example.crud_todolist.trello.validator.TrelloValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class TrelloFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    private final TrelloService trelloService;
    private final TrelloMapper trelloMapper;
    private final TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        if(trelloValidator.isValidRequest(trelloCard))
            return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
        return new CreatedTrelloCardDto("-1",
                "tried to test",
                "",
                    new TrelloBadgeDto(null,
                            false,
                            -1,
                            false,
                            false,
                            "",
                            -1,
                            -1,
                            LocalDateTime.now(),
                            -1,
                            -1,
                            false,
                            LocalDateTime.now(),
                            false,
                            LocalDateTime.now()));

    }


}
