package com.example.springsec.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
@SpringBootApplication
public class RestController {

    @GetMapping("/start")
    public String start() {
        return "Привет неавторизованный пользователь";
    }

    @GetMapping("/hello")
        public String hello(){
          return "привет авторизованный пользователь";
        }

    @GetMapping("/read")
    public String read(){
        return "Ну давай что-нибудь прочитаем";
    }

    @GetMapping("/write")
    public String write(){
        return "Ну давай что-нибудь напишем";
    }

}
