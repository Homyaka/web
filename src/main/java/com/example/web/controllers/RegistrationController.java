package com.example.web.controllers;
import com.example.web.models.User;
import com.example.web.reposit.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String home(Model model) {
        return "registration";
    }
    @PostMapping("/registration")
    public String registration(@RequestParam String username, @RequestParam String password,  Model model){
        Iterable<User> UserList= userRepository.findAll();
        if (password.length()!=8) {return "registration";}
        for (User u:UserList) {
            if (u.getLogin().equals(username)) {return "registration";}
        }
        User newUser=new User(username,password,"Outputname");
        userRepository.save(newUser);
        return "redirect:/";
    }
}