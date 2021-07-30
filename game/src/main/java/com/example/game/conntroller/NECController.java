package com.example.game.conntroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NECController {
    boolean isblackpanel =false;

    @RequestMapping("/getblackpanel")
    public boolean getblackpanel(){
        return isblackpanel;
    }

    @RequestMapping("/showblackpanel")
    public boolean showblackpanel(){
        isblackpanel=true;
        return isblackpanel;
    }
    @RequestMapping("/hideblackpanel")
    public boolean hideblackpanel(){
        isblackpanel=false;
        return isblackpanel;
    }

}
