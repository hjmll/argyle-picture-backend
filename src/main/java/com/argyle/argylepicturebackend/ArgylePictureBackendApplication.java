package com.argyle.argylepicturebackend;

import org.apache.shardingsphere.spring.boot.ShardingSphereAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@MapperScan("com.argyle.argylepicturebackend.mapper")
@SpringBootApplication(exclude = {ShardingSphereAutoConfiguration.class})
@EnableAspectJAutoProxy(exposeProxy = true)
public class ArgylePictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArgylePictureBackendApplication.class, args);
    }

}
