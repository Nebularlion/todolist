package com.example.Todolist.controller;

import com.example.Todolist.model.Task;
import com.example.Todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Returns every task to the user
     * @param model model to be given to Thymeleaf
     * @return returns tasklist HTML page
     */
    @RequestMapping("/tasklist")
    public String taskList(Model model){
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasklist";
    }

    /**
     * Returns empy task to the HTML
     * @param model
     * @return
     */
    @RequestMapping(value="/addtask")
    public String addTask(Model model){
        model.addAttribute("task", new Task());

        return "addtask";
    }

    /**
     * Saves task to the database
     * @param task Gets task as input from the user
     * @return Redirects user to the tasklist page
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Task task){
        taskRepository.save(task);

        return "redirect:/tasklist";
    }

    /**
     * Deletes task with matching id
     * @param id Gets id from url
     * @return Redirects to the tasklist
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public  String deleteTask(@PathVariable("id") Long id, Model model){
        taskRepository.delete(id);
        return "redirect:/tasklist";
    }

}