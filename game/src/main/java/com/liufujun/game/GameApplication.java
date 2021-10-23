package com.liufujun.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.liufujun.game.util.服务器使用路径;

@SpringBootApplication
public class GameApplication {
    public static void main(String[] args) {
        服务器使用路径 fw=new 服务器使用路径();
        SpringApplication.run(GameApplication.class, args);
    }

}
