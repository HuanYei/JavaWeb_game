package com.example.game.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class index {

    @RequestMapping("/")
    public String indexs(){
        return "index";
    }
}
