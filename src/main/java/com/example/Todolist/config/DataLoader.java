package com.example.Todolist.config;

import com.example.Todolist.model.Task;
import com.example.Todolist.model.User;
import com.example.Todolist.repository.TaskRepository;
import com.example.Todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

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



        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setUsername("user");
        user1.setPasswordHash("$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6");
        user1.setRole("USER");

        user2.setUsername("user2");
        user2.setPasswordHash("$2a$04$wz528SIdIAb4fCl3NmabnuouZWMMvRI1WvyLHD4b1JYcSo04hbTD.");
        user2.setRole("USER");

        user3.setUsername("admin");
        user3.setPasswordHash("testi1");
        user3.setRole("ADMIN");

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);
        user3 = userRepository.save(user3);

        task1.setUser(user1);
        task.setUser(user2);

        taskRepository.save(task);
        taskRepository.save(task1);


    }
}
