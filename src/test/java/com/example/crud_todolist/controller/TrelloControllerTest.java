package com.example.crud_todolist.controller;

import com.example.crud_todolist.domain.CreatedTrelloCardDto;
import com.example.crud_todolist.domain.TrelloBoardDto;
import com.example.crud_todolist.domain.TrelloCardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloControllerTest {

    @Autowired
    TrelloController controller;

    @Test
    void getTrelloBoards() {
        //when
        ResponseEntity<List<TrelloBoardDto>> response = controller.getTrelloBoards();
        int status = response.getStatusCodeValue();

        response.getBody().stream().map(TrelloBoardDto::getLists)
                .flatMap(Collection::stream)
                .forEach(trelloListDto -> System.out.println(trelloListDto.getId()));

        //then
        assertEquals(200, status);

    }

    @Test
    void createTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test","test","1","1");

        //when
        ResponseEntity<CreatedTrelloCardDto> response = controller.createTrelloCard(trelloCardDto);
        CreatedTrelloCardDto responseBody = response.getBody();

        //then
        assertEquals(200, response.getStatusCodeValue());
        assert responseBody != null;
        assertEquals("tried to test", responseBody.getName());
        assertEquals(-1, responseBody.getBadges().getVotes());
        assertFalse(responseBody.getBadges().isLocation());
        assertFalse(responseBody.getBadges().isViewingMemberVoted());
        assertFalse(responseBody.getBadges().isSubscribed());
        assertFalse(responseBody.getBadges().isDescription());
        assertFalse(responseBody.getBadges().isDueComplete());
    }
}