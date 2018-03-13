package com.example.Todolist.controller;

import com.example.Todolist.model.Task;
import com.example.Todolist.model.User;
import com.example.Todolist.repository.TaskRepository;
import com.example.Todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns every task to the user
     * @param model model to be given to Thymeleaf
     * @return returns tasklist HTML page
     */
    @RequestMapping("/tasklist")
    public String taskList(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername( userDetails.getUsername() );
        List<Task> tasks = taskRepository.findByUser(user);
        model.addAttribute("task1", new Task());
        model.addAttribute("tasks", tasks);
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername());

        task.setUser(user);
        taskRepository.save(task);

        return "redirect:/tasklist";
    }

    /**
     * Deletes task with matching id
     * @param id Gets id from url
     * @return Redirects to the tasklist
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public  String deleteTask(@PathVariable("id") Long id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        User user = userRepository.findByUsername( userDetails.getUsername() );
        Task task = taskRepository.findOne(id);

        if(task.getUser().equals(user)) taskRepository.delete(id);


        return "redirect:/tasklist";
    }

}