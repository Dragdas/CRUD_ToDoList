package com.example.crud_todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
/*@EnableSwagger2
@EnableWebMvc*/
public class CrudToDoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudToDoListApplication.class, args);
    }

/*    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(CrudToDoListApplication.class);
    }*/

}
