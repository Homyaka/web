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
import java.util.Random;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserRepository currentUserRepository;
    public int[] numarr=getNumbers(8);
    @GetMapping("/login")
    public String login( Model model) {
        String numpassword ="";
        //int[] numarr=getNumbers(8);
        for (int i=0;i<numarr.length;i++)
        {
            numpassword+=numarr[i]+1 +",";
        }
        model.addAttribute("numpassword","Enter "+ numpassword + "characters of your password");
        return "login";}

    @PostMapping("/login")
    public String loginIn(@RequestParam String username, @RequestParam String password,  Model model){
        User user= new User(username,password);
        CurrentUser curUser= new CurrentUser();
       // currentUserRepository.deleteAll();
        Iterable<User> users=userRepository.findAll();
        /*for (User u: users) {
            if (u.getLogin().equals(username)) {
                if (u.getPassword().equals(password)) {
                    curUser.setCurrentUser(username);
                    currentUserRepository.deleteAll();
                    currentUserRepository.save(curUser);
                    return "redirect:/";
                }
                else {
                   currentUserRepository.deleteAll();
                }
                }
            } */
        for(User u: users) {
            if (u.getLogin().equals(username)) {
               if (getPasswordSymbols(u.getPassword()).equals(password)){
                   curUser.setCurrentUser(username);
                   currentUserRepository.deleteAll();
                   currentUserRepository.save(curUser);
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

    public int[] getNumbers(int length){
        Random rand= new Random();
        int ammount=rand.nextInt(length-2)+2;//число знаков
        int[] arr = new int[ammount];
        for (int i=0;i<ammount;i++){
            arr[i]=rand.nextInt(length-1); //позиции знаков
        }
        return arr;
    }
    public String getPasswordSymbols(String password){
        char[] symbols= password.toCharArray();
        String passymb="";
        for (int i=0;i<numarr.length;i++){
            passymb+=symbols[numarr[i]];
        }
        return passymb;
    }
}

