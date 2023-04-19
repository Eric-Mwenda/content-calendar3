package com.ericmwenda.contentcalendar.controller;

import com.ericmwenda.contentcalendar.model.Content;
import com.ericmwenda.contentcalendar.model.ServerMath;
import com.ericmwenda.contentcalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Annotation to denote that this class is used as a RestAPI controller.
@RestController
//To Specify the path of this.
@RequestMapping("/api/v1/content")
//@CrossOrigin //for CORS: Values are set to default cors configs.
public class ContentController {
    //Do some dependency injection to the repository class containing the collection.
    //This is because when a request is made in this class.
    //The ContentCollectionRepository class has to be contacted and return the requested data.

    //Final to ensure that it can only be created once. and cannot be changed.
    private final ContentCollectionRepository repository;

    //To use the created SQL repository uncomment and use this as the repository then call the methods.
    //private final ContentJdbcTemplateRepository repository;

    //Having this as a constructor argument injects the repository to this class.
    //This is the same as using the @Autowired annotation.
    @Autowired
    //Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.
    //Similarly, if a class declares multiple constructors but none of them is annotated with @Autowired,
    // then a primary/default constructor (if present) will be used.
    // If a class only declares a single constructor to begin with, it will always be used, even if not annotated.
    // An annotated constructor does not have to be public.
    public ContentController(ContentCollectionRepository repository) {
        this.repository = repository;
    }
    /*public ContentController(ContentJdbcTemplateRepository repository){
        this.repository = repository;
    }*/

    /*public ContentController(){

        //This is a way to get the instance of the class normally, but the repository is already annotated by the
        //spring framework so there is already an instance of the class that is running. This creates a new instance.
        //Which is one that we don't want. -> Here is where dependency injection comes in.
        repository = new ContentCollectionRepository();

        //The recommended way is to get the repository from the Application Context via dependency injection.
        //For this controller to work, it depends on ContentCollectionRepository
    }*/

    //Since the ContentCollectionRepository class is injected in the constructor.
    //We can now make requests to the class to return the data that is needed to this Rest Controller class
    //that communicates to the outside world using endpoints defined here.

    //Get Request to find all pieces of content in the system.
    //GetMapping is used to handle GET requests to an API.
    @GetMapping("")//Path not specified so it will take the class level path which is "/api/v1/content" when a GET
    //request is made.

    //The repository class will return a list of Content when this method is called.
    public List<Content> findAll(){
        //Call the method that is defined by the repository using the object that has been injected here.
        return repository.findAll();
    }

    //GET endpoint to get a blog post by its id.
    @GetMapping("/{id}")//Get Annotation including a parameter for id in the path. This is a dynamic placeholder.
    //The GET url to this will be GET: .../api/v1/content/1
    public Content findById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found"));
    }

    //POST request to create a new blog post. uses @PostMapping.
    @ResponseStatus(HttpStatus.CREATED)//This is to inform the one making the request that the request was created.
                            //it returns a 201 status code.
    @PostMapping("")// Will use the class level path ".../api/v1/content" when a POST request is created.
    //By posting, we have to use a request body that will be sent as the body of the request.
    //To do so you have to specify in the parameter with the @RequestBody annotation.
    //@Valid annotation ensures that the body is always valid. otherwise, it wont execute saving.
    //You define what is valid can be defined in the model class of different properties such as @NotBlack, @Email, etc.
    public void create(@Valid @RequestBody Content content){
        //@RequestBody annotation indicates a method parameter should be bound to the body of the web request.

        //Will call the method on the repository to create a new record.
        repository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)//Return a status code to tell the content was successfully updated.
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id){

        //If the repository does not contain data with the selected id throw a not found exception.
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found");
        }
        //Use the save method since it will add the data to the collection list.
        repository.save(content);
    }

    @GetMapping("/getRandomMath")
    public ServerMath getRandomMath(){
        return repository.doRandomMath();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.delete(id);
    }

}
