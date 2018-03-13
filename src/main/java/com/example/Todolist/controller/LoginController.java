package com.example.Todolist.controller;

import com.example.Todolist.model.User;
import com.example.Todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("login")
    public String login(){

        return "login";
    }

    @RequestMapping("register")
    public String register(Model model){

        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Checking if User exists and if not User will be saved
     * @param user
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String userRegister(@ModelAttribute("user") User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user2 = userRepository.findByUsername(user.getUsername());

        if(Objects.nonNull(user2)) return "redirect:/register";

        user.setPasswordHash(encoder.encode(user.getPasswordHash()));
        user.setRole("USER");
        userRepository.save(user);

        return "redirect:/login";

    }


}