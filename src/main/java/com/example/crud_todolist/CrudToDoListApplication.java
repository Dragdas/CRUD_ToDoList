package com.example.crud_todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class CrudToDoListApplication extends SpringBootServletInitializer {
public class CrudToDoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudToDoListApplication.class, args);
    }

/*    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(CrudToDoListApplication.class);
    }*/

}
