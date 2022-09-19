package com.example.crud_todolist.trello.validator;

import com.example.crud_todolist.domain.TrelloBoard;
import com.example.crud_todolist.domain.TrelloCard;
import com.example.crud_todolist.domain.TrelloList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloValidatorTest {


    TrelloValidator validator = new TrelloValidator();

    @Test
    void validateCard() {
        //Given
        TrelloCard card = new TrelloCard("test", "dsc", "1","1");

        //when & then
        assertFalse(validator.isValidRequest(card));
    }

    @Test
    void validateTrelloBoards() {
        //given
        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards =
                List.of(
                        new TrelloBoard("1", "test", trelloLists),
                        new TrelloBoard("1", "normal board", trelloLists)
                        );

        //when
        List<TrelloBoard> validatedList = validator.validateTrelloBoards(trelloBoards);

        //then
        assertEquals(1, validatedList.size());

    }
}