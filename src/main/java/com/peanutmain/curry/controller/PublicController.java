package com.peanutmain.curry.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/public")
public class PublicController {

    @GetMapping("/version")
    public ResponseEntity<?> versionInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("rVersion", "f7014cc6a7");
        map.put("version", "1.0.0");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
