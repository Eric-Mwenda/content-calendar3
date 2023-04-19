package com.ericmwenda.contentcalendar.config;

import com.ericmwenda.contentcalendar.model.Content;
import com.ericmwenda.contentcalendar.repository.ContentSpringDataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

//This class has to be marked @Configuration to make sure it's a bean too.
@Configuration

//It implements Command line runner class which has a method that is called after dependency injection.
//That method run() can be a nice place to do data loading.
public class DataLoader implements CommandLineRunner {

    private final ContentSpringDataRepository springDataRepository; //Inject the repository class here to load json data.
    private final ObjectMapper objectMapper;//This is used to map json data to respective object classes.
    //It deserializes from json to an object.
    //The Object Mapper is also present in the Application Context.

    public DataLoader(ContentSpringDataRepository springDataRepository, ObjectMapper objectMapper){
        this.springDataRepository = springDataRepository;
        this.objectMapper = objectMapper;
    }
    @Override
    public void run(String... args) throws Exception {
        //This now runs after everything is initialized.

        //Load data from the json file in the resources/data/ directory.

        //We can load the data here by reading the dummy json data file in resources/data/dummydata.json
        //You have to try catch because of reading exceptions if they may occur.
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/dummyData.json")){
            //This will iterate through all the json file and map all into relevant Content Objects.
            //Then save all using Spring Data method that saves all in an Iterable.
            
            //if(springDataRepository.count() == 0){
            //    springDataRepository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){}));
            //}
            springDataRepository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){}));

        }catch (Exception e){
            System.out.println("Failed to Read Data: Error = " + e);
        }

    }
}
