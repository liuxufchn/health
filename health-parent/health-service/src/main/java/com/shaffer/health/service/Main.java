package com.shaffer.health.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @program: health
 * @Date: 2021/4/12 21:35
 * @Author: Shaffer
 * @Description:
 */
public class Main {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:spring-service.xml");
        System.in.read();
    }
}
