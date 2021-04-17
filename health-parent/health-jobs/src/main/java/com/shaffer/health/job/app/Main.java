package com.shaffer.health.job.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @program: health
 * @Date: 2021/4/16 20:52
 * @Author: Shaffer
 * @Description:
 */
public class Main{
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("spring-jobs.xml");
        System.in.read();
    }
}
