package com.ericmwenda.contentcalendar;

import org.springframework.stereotype.Component;

@Component //Class level annotation
        // Indicates that an annotated class is a "component". Such classes are considered as candidates
        // for auto-detection when using annotation-based configuration and classpath scanning.
public class Message {
    public String getMessage(){
        return "Hello World!";
    }
}
