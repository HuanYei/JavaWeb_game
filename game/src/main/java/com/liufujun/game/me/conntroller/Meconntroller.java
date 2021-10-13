package com.liufujun.game.me.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Meconntroller {

    @RequestMapping("/toMeworkbench")
    public String  toPDF(){
        System.out.println(4);
        return "meindex";
    }
}
