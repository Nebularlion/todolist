package com.example.Todolist;

import com.example.Todolist.model.User;
import com.example.Todolist.repository.UserRepository;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Before
    public void setUp() {
        User user = new User();

        user.setUsername("Testuser");
        user.setPasswordHash("Testest");
        user.setRole("USER");

        repository.save(user);
    }

    @After
    public void tearDown() {
        List<User> users = (List<User>) repository.findAll();

        for (User user : users) {
            repository.delete(user.getId());
        }

    }

    @Test
    public void findByUsernameShouldReturnUser() {
        User users = repository.findByUsername("Testuser");
        assertThat(users.getUsername()).isEqualTo("Testuser");
    }

    @Test
    public void createNewUser() {
        User user = new User("Testuser2", "Test2", "testemail2", "USER");
        repository.save(user);
        assertThat(user.getId()).isNotNull();
    }
}