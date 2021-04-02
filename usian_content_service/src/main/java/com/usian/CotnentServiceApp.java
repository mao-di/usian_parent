package com.usian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:CotnentServiceApp
 * Author:maodi
 * CreateTime:2021/03/15/21:03
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.usian.mapper")
public class CotnentServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CotnentServiceApp.class, args);
    }
}
