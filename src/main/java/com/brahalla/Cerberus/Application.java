package com.brahalla.Cerberus;

import com.brahalla.Cerberus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
