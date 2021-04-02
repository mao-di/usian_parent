package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:ContentWebApp
 * Author:maodi
 * CreateTime:2021/03/15/21:06
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ContentWebApp {
    public static void main(String[] args) {
        SpringApplication.run(ContentWebApp.class, args);
    }
}
