package com.ericmwenda.contentcalendar;

import com.ericmwenda.contentcalendar.config.HomepageConfiguration;
import com.ericmwenda.contentcalendar.model.Content;
import com.ericmwenda.contentcalendar.model.Status;
import com.ericmwenda.contentcalendar.model.Type;
import com.ericmwenda.contentcalendar.repository.ContentSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;

//While using configuration properties for a certain class, you have to enable the Config prop using the annotation.
//the providing the class to enable.
@EnableConfigurationProperties(HomepageConfiguration.class)
@SpringBootApplication
public class ContentCalendarApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ContentCalendarApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(ContentCalendarApplication.class, args);

		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		//var beans = context.getBeanDefinitionNames();

		//Get a bean by the name. In this case it's a bean defined by a method.
        /* RestTemplate restTemplate = (RestTemplate) context.getBean("restTemplates");
		RestTemplate restTemplate2 = (RestTemplate) context.getBean("restTemplate");
		System.out.println(restTemplate);
		System.out.println(restTemplate2);*/
	}

	//Another way to load data at the start, or basically call a method when the application starts is by the use
	//of a Functional Interface, Command Line runner then provide the args with what we wanna run.
	//You have to denote it as @Bean to ensure spring creates an instance of this method and manages it.

	//@Bean//If you want to create an instance of a class using a method.
	//For this method to be called it has to have the @Bean annotation to be available and managed by spring.
	@Autowired//Dependency injection of the spring repository is automatically done though.
	CommandLineRunner commandLineRunner(ContentSpringDataRepository springDataRepository){

		//Since spring is managing the ContentSpringDataRepository in the Application context level.
		//It will see that we have created an instance of it in the method parameter and Autowire it automatically.

		//Since the Command Line runner run() methods takes vargs as the parameter, you can provide the arguments
		//in the lambda expression.
		return args -> {
			System.out.println("THIS IS A VARGS ARGUMENT. DAtA LOADING CAN BE DONE HERE.....");

			//Insert data into the spring repository.
			Content blogPost1 = new Content(
					null,//providing id as null so that the database manages auto incrementing the id.
					"Data Loading Using Functional Interface.",
					"Shows Data Loading in spring boot using a functional interface.",
					Status.IDEA,
					Type.VIDEO,
					LocalDateTime.now(),
					LocalDateTime.now(),
					"http://127.0.0.1:8080/api/v1/content"
			);

			Content blogPost2 = new Content(
					null,
					"Ways of Data Loading in Spring Boot.",
					"There is the Configuration way, Post construct way and this functional interface.",
					Status.IN_PROGRESS,
					Type.ARTICLE,
					LocalDateTime.now(),
					LocalDateTime.now(),
					"http://127.0.0.1:8080/api/v1/content"
			);

			springDataRepository.save(blogPost1);
			springDataRepository.save(blogPost2);
		};
	}
}
