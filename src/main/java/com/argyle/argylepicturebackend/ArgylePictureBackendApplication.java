package com.argyle.argylepicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@MapperScan("com.argyle.argylepicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class ArgylePictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArgylePictureBackendApplication.class, args);
    }

}
