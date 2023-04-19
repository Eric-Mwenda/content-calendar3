package com.ericmwenda.contentcalendar.controller;

import com.ericmwenda.contentcalendar.config.HomepageConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomepageController{

    //You can define variable values in the application.properties file which you can use to display.

    //these cc.{variableName} values are defined in the application.properties file.
    //you can use any name actually doesn't have to be cc. somethings, cc is for content calendar.
    @Value("${cc.welcomeMessage: This Denotes Default Welcome Message if not Defined.}")
    private String welcomeMessage;
    @Value("${cc.about: Default About Message if about not defined.}")
    private String about;

    //@Value annotation -> Annotation used at the field or method/constructor parameter level
    // that indicates a default value expression for the annotated element
    // A common use case is to inject values using #{systemProperties.myProp}
    // style SpEL (Spring Expression Language) expressions.
    // Alternatively, values may be injected using ${my.app.myProp} style property placeholders.


    //@GetMapping("/")//Mapping for root path.
    public String homeString(){
        return welcomeMessage;
    }

    //inorder to return json. you can have this method that returns a map containing the welcome message and the about.
    //@GetMapping("/")
    public Map<String, String> homePage()
    {
        return Map.of("welcomeMessage", welcomeMessage, "about", about);
    }

    //Now using the configuration class.
    //we have to inject the class to this method so as to return its json in the root path.
    private final HomepageConfiguration configuration;
    @Autowired
    public HomepageController(HomepageConfiguration configuration){
        this.configuration = configuration;
    }
    @GetMapping("/")
    public HomepageConfiguration home(){
        //This will now return the json of the configuration class that has been injected above.
        //whose variable values are contained in the application.properties file.
        return configuration;
    }

    @GetMapping("/home2")//The values of this route are both null
    @Autowired//Dependency injection not done correctly.
    public HomepageConfiguration home(HomepageConfiguration configuration){
        return configuration;
    }
}
