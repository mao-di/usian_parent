package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:DetailWebApp
 * Author:maodi
 * CreateTime:2021/03/25/11:18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class DetailWebApp {
    public static void main(String[] args) {
        SpringApplication.run(DetailWebApp.class, args);
    }
}
