package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:SearchWebApp
 * Author:maodi
 * CreateTime:2021/03/24/15:43
 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class SearchWebApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchWebApp.class, args);
    }
}
