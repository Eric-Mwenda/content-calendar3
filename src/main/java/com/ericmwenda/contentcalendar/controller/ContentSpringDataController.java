package com.ericmwenda.contentcalendar.controller;

import com.ericmwenda.contentcalendar.model.Content;
import com.ericmwenda.contentcalendar.model.Status;
import com.ericmwenda.contentcalendar.repository.ContentSpringDataRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/content/springData")
public class ContentSpringDataController {

    //Dependency injection to the Spring data class(interface)without any crud methods.
    private final ContentSpringDataRepository springDataRepository;

    @Autowired
    public ContentSpringDataController(ContentSpringDataRepository springDataRepository){
        this.springDataRepository= springDataRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllContent")
    public List<Content> findAll(){
        return springDataRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getContentById/{id}")
    public Content findById(@PathVariable Integer id){
        return springDataRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content Not Found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createContent")
    public void createContent(@Valid @RequestBody Content content){
        springDataRepository.save(content);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/updateContentById/{id}")
    public void updateContent(@PathVariable Integer id, @Valid @RequestBody Content content){
        if(!springDataRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content to update not found.");
        }
        springDataRepository.save(content);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/deleteContentById/{id}")
    public void deleteContent(@PathVariable Integer id){
        if(!springDataRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content to delete not found.");
        }
        springDataRepository.deleteById(id);
    }

    //Using Spring data we may want a custom route for a method that is not among the default.
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findBy/keyword/{keyword}")
    public List<Content> findByKeyword(@PathVariable String keyword){
        return springDataRepository.findAllByTitleContainsIgnoreCase(keyword);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/findBy/status/{status}")
    public List<Content> findByStatus(@PathVariable Status status){
        return springDataRepository.findAllByStatus(status);
    }

}
