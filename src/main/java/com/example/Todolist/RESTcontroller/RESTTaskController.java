package com.example.Todolist.RESTcontroller;


import com.example.Todolist.model.Task;
import com.example.Todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class RESTTaskController {

    @Autowired
    private TaskRepository taskRepository;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/tasklist")
    public List<Task> taskList() { return (List) taskRepository.findAll(); }

    @RequestMapping("/tasklist/{id}")
    public Task getTask(@PathVariable("id") Long id) { return taskRepository.findOne(id); }
}
