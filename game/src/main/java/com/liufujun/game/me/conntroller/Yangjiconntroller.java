package com.liufujun.game.me.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Yangjiconntroller {
    @RequestMapping("/toYangji")
    public String toSWtool(Model model){
        return "Prototyping/yangji";
    }

}
