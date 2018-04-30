package com.peanutmain.curry.controller;

import com.peanutmain.curry.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    @GetMapping("/v1/user/{id}")
    public ResponseEntity<?> info(@PathVariable("id") Long id) {
        User user = new User(id);
        user.setEmail("zhangsan@gmail.com");
        user.setPhone("10086");
        user.setUsername("zhangsan");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
