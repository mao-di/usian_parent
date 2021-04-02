package com.usian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ClassName:SSOServiceApp
 * Author:maodi
 * CreateTime:2021/03/26/09:19
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.usian.mapper")
public class SSOServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(SSOServiceApp.class, args);
    }
}
