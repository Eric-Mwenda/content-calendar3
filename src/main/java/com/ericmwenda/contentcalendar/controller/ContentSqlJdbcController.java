package com.ericmwenda.contentcalendar.controller;

import com.ericmwenda.contentcalendar.model.ContentJdbc;
import com.ericmwenda.contentcalendar.repository.ContentJdbcTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/content/jdbc")
public class ContentSqlJdbcController {

    private final ContentJdbcTemplateRepository jdbcTemplateRepository;

    @Autowired
    public ContentSqlJdbcController(ContentJdbcTemplateRepository jdbcTemplateRepository){
        this.jdbcTemplateRepository = jdbcTemplateRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getAllContent")
    public List<ContentJdbc> getAllContent(){
        return jdbcTemplateRepository.getAllContent();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/createContent")
    public void createContent(@RequestBody ContentJdbc contentJdbc){
        jdbcTemplateRepository.createContent(contentJdbc.title(), contentJdbc.description(),contentJdbc.status(),
                contentJdbc.contentType(), contentJdbc.url());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/updateContent/{id}")
    public void updateContent(@PathVariable int id, @RequestBody ContentJdbc contentJdbc){
        if(!jdbcTemplateRepository.contentByIdExists(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content you are trying to update NOT FOUND!");
        }

        jdbcTemplateRepository.updateContent(id, contentJdbc.title(), contentJdbc.description(), contentJdbc.status(),
                contentJdbc.contentType(), contentJdbc.url());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/getContentById/{id}")
    public ContentJdbc getContentById(@PathVariable int id){
        if(!jdbcTemplateRepository.contentByIdExists(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content you are trying to get NOT FOUND!");
        }

        return jdbcTemplateRepository.getContentById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{id}")
    public void deleteContentById(@PathVariable int id){
        jdbcTemplateRepository.deleteContent(id);
    }
}
