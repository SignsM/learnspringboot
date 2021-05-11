package com.signs123.baiyunbbs;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.signs123.baiyunbbs.mapper")
public class BaiyunbbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaiyunbbsApplication.class, args);
    }

}
