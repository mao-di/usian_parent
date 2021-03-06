package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:CartWebApp
 * Author:maodi
 * CreateTime:2021/03/29/08:39
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CartWebApp {
    public static void main(String[] args) {
        SpringApplication.run(CartWebApp.class, args);
    }
}
