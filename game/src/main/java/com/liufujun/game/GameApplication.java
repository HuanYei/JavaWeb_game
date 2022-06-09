package com.liufujun.game;

import com.liufujun.game.config.FaeUpdate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.liufujun.game.util.服务器使用路径;

@SpringBootApplication
public class GameApplication {
    public static void main(String[] args) {
        FaeUpdate.autoUpdate();
        服务器使用路径 fw=new 服务器使用路径();
        SpringApplication.run(GameApplication.class, args);
    }

}
