package com.example.crud_todolist.repository;

import com.example.crud_todolist.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAll();
}
