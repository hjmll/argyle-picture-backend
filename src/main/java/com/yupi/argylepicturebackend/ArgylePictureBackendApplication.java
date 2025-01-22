package com.yupi.argylepicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.yupi.argylepicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class ArgylePictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArgylePictureBackendApplication.class, args);
    }

}
