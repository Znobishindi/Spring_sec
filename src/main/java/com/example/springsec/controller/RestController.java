package com.example.springsec.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@org.springframework.web.bind.annotation.RestController
@SpringBootApplication
public class RestController {

    @GetMapping("/start")
    public String start() {
        return "Привет неавторизованный пользователь";
    }

    @PreAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping("/hello")
    public String hello() {
        return "привет авторизованный пользователь";
    }
    @PostAuthorize("hasAnyRole('READ','WRITE')")
    @GetMapping("/hello-peace")
    public String helloPeace() {
        return "привет авторизованный пользователь и мира тебе!";
    }

    @Secured({"ROLE_READ"})
    @GetMapping("/read")
    public String read() {
        return "Ну давай что-нибудь прочитаем";
    }

    @RolesAllowed({"ROLE_WRITE"})
    @GetMapping("/write")
    public String write() {
        return "Ну давай что-нибудь напишем";
    }
    @PreAuthorize("#userName == authentication.principal.username")
    @GetMapping("/good-bye")
    public String goodBye(String userName) {
        return "Ну давай, " + userName + "! Еще увидимся!";
    }


}
