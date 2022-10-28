package com.example.web.controllers;
import com.example.web.models.CurrentUser;
import com.example.web.models.User;
import com.example.web.reposit.CurrentUserRepository;
import com.example.web.reposit.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
   private CurrentUserRepository currentUserRepository;

    @GetMapping("/")
    public String main( Model model) {
        Iterable<User> users= userRepository.findAll();
        Iterable<CurrentUser> currentUser= currentUserRepository.findAll();
        if (currentUser!=null){
            model.addAttribute("currentUser",currentUser);
        }
        else {
            model.addAttribute("currentUser","Nobody");
        }
        model.addAttribute("users",users);
        return "home";
    }
}
