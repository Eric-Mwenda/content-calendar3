package com.ericmwenda.contentcalendar.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//If we want this class to run in the production profile. We can use the @Profile Annotation.
@Profile("!production")//This specifies that this class / bean is not run in the dev profile that we are currently in.
//if we had a profile such as local, and you want this to only run there you could've used.
//@Profile("local") Annotation.
@Configuration //Class level annotation of Configuration.
            //Indicates that a class declares one or more @Bean methods and may be processed by the Spring container
            // to generate bean definitions and service requests for those beans at runtime
public class WebConfiguration implements CommandLineRunner {

    @Bean //Indicates that a method produces a bean to be managed by the spring container.
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }

    @Bean RestTemplate restTemplates(){
        return new RestTemplateBuilder().build();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("THIS RUNS IN ALL PROFILES APART FROM production PROFILE.");
    }
}
