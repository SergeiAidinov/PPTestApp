package ru.yandex.incoming34.PPTestApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@EnableWebMvc
public class Controller {

    @GetMapping("payment")
    public ResponseEntity<String> payment(){
        return new ResponseEntity<>("Works!!!", HttpStatus.OK);
    }


}
