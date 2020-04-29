package com.ml.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.ml.mapper")
@ComponentScan(basePackages = {"com.ml.service","com.ml.mapper","com.ml.web.controller"})
public class DemoWebApplication /*extends SpringBootServletInitializer*/ {

    /**
     * 项目如果要打war包;
     * [1:pom.xml中去掉springboot内置tomcat，并引入外部tomcat支持;
     * 2:springboot启动类(多模块时在web模块)继承SpringBootServletInitializer并重写configure方法]
     **/
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoWebApplication.class);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(DemoWebApplication.class, args);
    }

}
