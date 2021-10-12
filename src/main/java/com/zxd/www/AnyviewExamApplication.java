package com.zxd.www;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Makonike
 */
@SpringBootApplication
@MapperScan("com.zxd.www.*.mapper")
public class AnyviewExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyviewExamApplication.class, args);
    }

}
