package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * ClassName:EurekaApp
 * Author:maodi
 * CreateTime:2021/03/11/12:30
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApp{

    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class, args);
    }
}
