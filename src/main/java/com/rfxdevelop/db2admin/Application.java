package com.rfxdevelop.db2admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "com.rfxdevelop.db2admin")
@ServletComponentScan(basePackages = "com.rfxdevelop.db2admin.filter")
@MapperScan("com.rfxdevelop.db2admin.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
