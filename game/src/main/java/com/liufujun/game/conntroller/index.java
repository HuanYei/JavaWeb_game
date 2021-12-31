package com.liufujun.game.conntroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class index {

    @RequestMapping("/tool")
    public String indexs(){
        return "index";
    }
}
