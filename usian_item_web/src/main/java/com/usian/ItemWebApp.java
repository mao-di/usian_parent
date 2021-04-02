package com.usian;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
 * ClassName:ItemWebApp
 * Author:maodi
 * CreateTime:2021/03/11/12:49
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ItemWebApp {
    public static void main(String[] args) {
        SpringApplication.run(ItemWebApp.class, args);
    }
}
