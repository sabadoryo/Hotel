package kz.iitu.javaee.ilyasProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//Zhanbolat super proger
//super puper

//check from AskeSaske test
//Zhanbolat super proger
//super puper
//asdasdasdasdasdasdasdasdasdasdass
@Controller
public class MainController {

    @GetMapping(path = "/")
    public String index(Model model){
        return "index";
    }

    @GetMapping(path = "/login")
    public String login(Model model){
        return "annonymous/login";
    }

    @GetMapping(path = "/profile")
    public String profile(Model model){
        return "profile";
    }

    @GetMapping(path = "/profile")
    public String gaga(Model model){
        return "profile";
    }

    @GetMapping(path = "/Asdasdsae")
    public String yernar(Model model){
        return "profile";
    }


    @GetMapping(path = "/novaya_functions")
    public String asdsdasd(Model model){
        return "profile";
    }

    public void kek(){
        int count = 0;
    }

}
