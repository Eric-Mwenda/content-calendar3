package com.ericmwenda.contentcalendar.repository;

import com.ericmwenda.contentcalendar.model.Content;
import com.ericmwenda.contentcalendar.model.ServerMath;
import com.ericmwenda.contentcalendar.model.Status;
import com.ericmwenda.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

//To Keep a collection of resources in memory to emulate a database.
//When marked with this class level annotation. It means that there is already an instance that will be created by the
//framework in the Application Context. This means that we should not create a new() instance of this class anywhere.
//Rather -> use dependency injection.
@Repository
public class ContentCollectionRepository {
    //To Act as an in memory database list.
    private final List<Content> contentList = new ArrayList<>();
    private final ServerMath serverMath = new ServerMath();
    Random random = new Random();


    public ContentCollectionRepository(){

    }

    public List<Content> findAll(){
        return contentList;
    }

    //Find a given item specified by its id.
    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content){
        //when adding new content. If one with an id that is the same as the constructors content id..
        //it means that we are updating that content so we can remove the existing the add in the new one.
        contentList.removeIf(c -> c.id().equals(content.id()));

        contentList.add(content);
    }

    public void delete(Integer id){
        contentList.removeIf(c -> c.id().equals(id));
    }

    public boolean existsById(Integer id){
        //look at all the data elements and check if any has the id provided in the parameter. if 0 = false, 1 = true.
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    //A Post construct annotation to a method refers to a method that is called after dependency injection is made.
    //This is to perform any initialization.
    @PostConstruct
    private void init(){
        //This will be used to initialize some dummy data to the list which will be used in the RestController.
        Content blogPost1 = new Content(
                1,
                "My First Blog Post",
                "Describing my first blog post.",
                Status.IDEA,
                Type.VIDEO,
                LocalDateTime.now(),
                null,
                "http://127.0.0.1:8080/api/v1/content"
        );

        Content blogPost2 = new Content(
                2,
                "My Second Blog Post",
                "This is my second cool article.",
                Status.IN_PROGRESS,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "http://127.0.0.1:8080/api/v1/content"
        );

        contentList.add(blogPost1);
        contentList.add(blogPost2);
    }

    //Random Server math methods.
    public ServerMath doRandomMath(){
        switch (random.nextInt(4)) {
            case 0 -> randomAddition();
            case 1 -> randomSubtraction();
            case 2 -> randomMultiplication();
            case 3 -> randomDivision();
        }
        return serverMath;
    }

    private void randomAddition(){
        serverMath.mathOperation = ServerMath.MathOperation.ADDITION;
        serverMath.firstNumber = random.nextInt(50);
        serverMath.secondNumber = random.nextInt(50);
        serverMath.result = serverMath.firstNumber + serverMath.secondNumber;
    }
    private void randomSubtraction(){
        serverMath.mathOperation = ServerMath.MathOperation.SUBTRACTION;
        int firstNumber, secondNumber;
        do {
            firstNumber = random.nextInt(99);
            secondNumber = random.nextInt(99);

        } while (firstNumber <= secondNumber);

        serverMath.firstNumber = firstNumber;
        serverMath.secondNumber = secondNumber;
        serverMath.result = firstNumber - secondNumber;
    }

    private void randomMultiplication(){
        serverMath.mathOperation = ServerMath.MathOperation.MULTIPLICATION;
        serverMath.firstNumber = random.nextInt(50);
        serverMath.secondNumber = random.nextInt(50);
        serverMath.result = serverMath.firstNumber * serverMath.secondNumber;
    }

    private void randomDivision(){
        serverMath.mathOperation = ServerMath.MathOperation.DIVISION;
        int firstNumber, secondNumber;

        do {
            firstNumber = random.nextInt(99);
            secondNumber = random.nextInt(99);

        } while (firstNumber == 0 || secondNumber == 0 || firstNumber % secondNumber != 0);

        serverMath.firstNumber = firstNumber;
        serverMath.secondNumber = secondNumber;
        serverMath.result = firstNumber / secondNumber;
    }
}
