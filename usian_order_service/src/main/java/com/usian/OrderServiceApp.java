package com.usian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:OrderServiceApp
 * Author:maodi
 * CreateTime:2021/03/29/11:53
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.usian.mapper")
public class OrderServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApp.class, args);
    }
}
