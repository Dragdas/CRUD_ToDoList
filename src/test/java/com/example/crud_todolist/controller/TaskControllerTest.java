package com.example.crud_todolist.controller;

import com.example.crud_todolist.domain.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskControllerTest {

    @Autowired
    TaskController taskController;


    @Test
    void getTasks() {
        //when & then
        Assertions.assertDoesNotThrow(()->taskController.getTasks());
    }

    @Test
    void getTask(){
        //given
        boolean threwException = false;

        //when
        try {
            ResponseEntity<TaskDto> response = taskController.getTask(-1L);
        }catch (TaskNotFoundException e){
            threwException = true;
        }

        //then
        assertTrue(threwException);
    }


    @Test
    void createAndDeleteTask() {
        //given
        TaskDto taskToBeSaved = new TaskDto(null,"test", "content");

        //create
        try{
            //when
            int numberOfTasks = taskController.getTasks().getBody().size();
            taskController.createTask(taskToBeSaved);
            int numberOfTasksAfterAddition = taskController.getTasks().getBody().size();

            //then
            assertTrue(numberOfTasksAfterAddition > numberOfTasks);

        }catch (Exception e){
            fail("Should not throw");
        }


        //delete
        int taskAmountBefore = taskController.getTasks().getBody().size();
        List<TaskDto> tasks = taskController.getTasks().getBody();
        TaskDto addedTask = tasks.get(tasks.size()-1);
        taskController.deleteTask(addedTask.getId());
        int taskAmountAfter = taskController.getTasks().getBody().size();
        assertTrue(taskAmountBefore > taskAmountAfter);
    }


}