package com.example.Todolist.repository;

import com.example.Todolist.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

   List<Task> findByTitle(String title);
}
