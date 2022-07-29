package com.example.crud_todolist;


import com.example.crud_todolist.domain.Task;
import com.example.crud_todolist.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskAddingTest {

    @Autowired
    DbService DbService;


    @Test
    void addTask(){

        Task task = new Task(10L,"test testow", "test");

        DbService.saveTask(task);

    }

}
