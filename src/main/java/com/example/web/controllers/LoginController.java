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

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserRepository currentUserRepository;

    @GetMapping("/login")
    public String log( Model model) {;
        return "login";
    }
    @GetMapping("/home")
    public String home( Model model) {;
        return "home";
    }
   /* @GetMapping("/loginIn")
    public String loginIn(Model model) {
        return "loginIn";
    }*/

    @PostMapping("/login")
    public String loginIn(@RequestParam String username, @RequestParam String password,  Model model){
        User user= new User(username,password);
        CurrentUser curUser= new CurrentUser();
       // currentUserRepository.deleteAll();
        Iterable<User> users=userRepository.findAll();
        for (User u: users) {
            if (u.getLogin().equals(username)) {
                if (u.getPassword().equals(password)) {
                    curUser.setCurrentUser(username);
                    return "redirect:/";
                }
                else {
                   currentUserRepository.deleteAll();
                }
                }
            }
       return "login";
    }
    /* public String GetCurrentUser(){
        return currentUser;
    }*/
}
