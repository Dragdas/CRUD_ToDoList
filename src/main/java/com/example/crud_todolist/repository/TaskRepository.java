package com.example.crud_todolist.repository;

import com.example.crud_todolist.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
