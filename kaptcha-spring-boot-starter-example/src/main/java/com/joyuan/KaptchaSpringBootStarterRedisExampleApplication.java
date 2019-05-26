package com.joyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class KaptchaSpringBootStarterRedisExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaptchaSpringBootStarterRedisExampleApplication.class, args);
    }

}
