package com.usian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName:SSOWebApp
 * Author:maodi
 * CreateTime:2021/03/26/09:21
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SSOWebApp {
    public static void main(String[] args) {
        SpringApplication.run(SSOWebApp.class, args);
    }
}
