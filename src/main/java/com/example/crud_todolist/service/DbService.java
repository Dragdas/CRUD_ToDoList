package com.example.crud_todolist.service;

import com.example.crud_todolist.domain.Task;
import com.example.crud_todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(long id){
        if (repository.findById(id).isEmpty())
            throw new RuntimeException("There is no task with id " + id);
        return repository.findById(id).get();
    }

}