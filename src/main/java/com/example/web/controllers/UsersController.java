package com.example.web.controllers;
import com.example.web.models.CurrentUser;
import com.example.web.models.User;
import com.example.web.reposit.CurrentUserRepository;
import com.example.web.reposit.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserRepository currentUserRepository;

    @GetMapping("/users")
    public String user(Model model){
        Iterable<CurrentUser> cUser= currentUserRepository.findAll();
        Iterable<User> users=userRepository.findAll();
        for(CurrentUser cu:cUser)
        {
            
        }
        model.addAttribute("users",users);
        return "users";
    }
    @GetMapping("users/del")
    public String delUser(Model model){ return "users";}
    @GetMapping("users/new")
    public String newUser(Model model){ return "users";}
    @PostMapping("/users/new")
    public String newUser(@RequestParam String createuser,@RequestParam String createpassword,@RequestParam String useraction, Model model){
        if(createuser!=null && createpassword!=null) {
            User newUser = new User(createuser, createpassword, useraction);
            userRepository.save(newUser);
        }
        return "users";
    }
    @PostMapping("/users/del")
    public String delUser(@RequestParam String deluser, Model model){
        Iterable<User> users= userRepository.findAll();
        for (User u:users) {
            if(u.getLogin().equals(deluser)){
                userRepository.delete(u);
            }
        }
        return "users";
    }
}
