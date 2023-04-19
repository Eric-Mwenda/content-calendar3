package com.ericmwenda.contentcalendar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

//This is a configuration class thats used to configure the messages displayed in the Homepage controller.
//This class values are defined in the application.properties file with the variable names starting with cc.
//This configuration class has to be enabled in the root start application class containing the main() method.
@ConfigurationProperties(value = "cc")
//Annotation for externalized configuration.
// Add this to a class definition or a @Bean method in a @Configuration class
// if you want to bind and validate some external Properties (e.g. from a .properties file).
public record HomepageConfiguration(String welcomeMessage, String about) {
}
