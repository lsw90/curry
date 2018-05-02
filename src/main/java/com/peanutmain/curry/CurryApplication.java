package com.peanutmain.curry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.peanutmain.curry.dao")
public class CurryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurryApplication.class, args);
    }
}
