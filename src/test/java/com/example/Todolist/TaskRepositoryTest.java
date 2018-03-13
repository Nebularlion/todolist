package com.example.Todolist;

import com.example.Todolist.model.Task;
import com.example.Todolist.repository.TaskRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository repository;

    @Before
    public void setUp(){
        Task task = new Task();

        task.setTitle("Clean floor");
        task.setDescription("With soap");
        task.setPriority(4);

        repository.save(task);
    }

    @After
    public void tearDown(){
        List<Task> tasks = (List<Task>) repository.findAll();

        for (Task task : tasks){
            repository.delete(task.getId());
        }

    }

    @Test
    public void createNewTask(){
        Task task = new Task();
        repository.save(task);
        assertThat(task.getId()).isNotNull();
    }
}
