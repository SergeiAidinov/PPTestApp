package ru.yandex.incoming34.PPTestApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class Controller {

    @GetMapping("payment")
    public ResponseEntity<String> payment(){
        return new ResponseEntity<>("Works!!!", HttpStatus.OK);
    }


}
