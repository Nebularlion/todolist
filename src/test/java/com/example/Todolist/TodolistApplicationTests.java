package com.example.Todolist;

import com.example.Todolist.controller.TaskController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodolistApplicationTests {

	@Autowired
	private TaskController controller;

	@Test
	public void contextLoads() { assertThat(controller).isNotNull();}

}
