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

@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserRepository currentUserRepository;

    @GetMapping("/users")
    public String user(Model model){
        Iterable<User> users=userRepository.findAll();
        model.addAttribute("users",users);
        return "users";
    }
    @PostMapping("/users")
    public String delUser(Model model){
        return "users";
    }
}
