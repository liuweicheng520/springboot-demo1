package com.sy.springbootdemo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sy.springbootdemo1.dao")
public class SpringbootDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo1Application.class, args);
    }

}
