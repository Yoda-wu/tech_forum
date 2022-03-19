package com.uml.projectapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author d1286
 */
@SpringBootApplication(scanBasePackages = {"com.uml"})
@MapperScan("com.uml.projectapp.dao")
@EnableOpenApi
public class ProjectAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAppApplication.class, args);
    }

}
