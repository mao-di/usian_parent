package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:OrderWebApp
 * Author:maodi
 * CreateTime:2021/03/29/11:55
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderWebApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderWebApp.class, args);
    }
}
