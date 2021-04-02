package com.usian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * ClassName:ItemServiceApp
 * Author:maodi
 * CreateTime:2021/03/11/12:43
 */
@SpringBootApplication
@EnableEurekaClient//允许向服务端注册服务
@EnableDiscoveryClient
@MapperScan("com.usian.mapper")
public class ItemServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApp.class, args);
    }
}
