package com.example.Todolist.config;

import com.example.Todolist.model.Task;
import com.example.Todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... strings) throws Exception {

        /**
         * Inserts default data to database for testing purposes
         */
        Task task = new Task();
        Task task1 = new Task();

        task.setTitle("Clean room");
        task.setDescription("Remember to wipe dust from the shelf");
        task.setPriority(2);

        task1.setTitle("Take the dog for a walk");
        task1.setDescription("Use the short leash");
        task1.setPriority(1);

        taskRepository.save(task);
        taskRepository.save(task1);
    }
}
