package com.example.crud_todolist.controller;

import com.example.crud_todolist.domain.Task;
import com.example.crud_todolist.domain.TaskDto;
import com.example.crud_todolist.mapper.TaskMapper;
import com.example.crud_todolist.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskController {

    private final DbService service;
    private final TaskMapper taskMapper;


    @GetMapping
    @CrossOrigin("*")
    public ResponseEntity<List<TaskDto>> getTasks() {
        return ResponseEntity.ok(taskMapper.mapToTaskDtoList(service.getAllTasks()));
    }

    @GetMapping(value = "{taskId}")
    @CrossOrigin("*")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(taskMapper.mapToTaskDto(service.getTask(taskId)));
    }

    @DeleteMapping
    @CrossOrigin("*")
    public ResponseEntity<Void> deleteTask(@RequestParam Long taskId) {
        try {
            service.deleteTask(taskId);
            return ResponseEntity.ok().build();
        }catch (TaskNotFoundException exception){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @CrossOrigin("*")
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(savedTask));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin("*")
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
        return ResponseEntity.ok().build();
    }
}
