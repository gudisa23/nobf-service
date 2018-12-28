package com.att.api.nobf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = { "com.att", "com.bcg", "com.amind.att", "com.directv" })
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@EnableSwagger2
@EnableMongoAuditing
@PropertySource({ "classpath:application.properties" })
public class Api {
    public static void main(final String[] args) {
        SpringApplication.run(Api.class, args);
    }
}
