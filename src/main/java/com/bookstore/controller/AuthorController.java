package com.bookstore.controller;

import com.bookstore.bean.Author;
import com.bookstore.bean.AuthorRequest;
import com.bookstore.bean.Status;
import com.bookstore.service.AuthorService;
import com.bookstore.service.BookService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {


    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);


    @Autowired
    private AuthorService authorService;

    @PostMapping("/addauthordetails")
    public String addAuthorDetails(@RequestBody AuthorRequest authorRequest) {
        String message = authorService.saveAuthorDetails(authorRequest);
        return message;
    }

    /**
     * To get all book authors details
     * @return authors
     */
    @GetMapping("/getauthordetails")
    public String getAuthorDetails(){
        String response = null;
        try{
            List<Author> authors = authorService.getAuthorDetails();
            response = new Gson().toJson(authors);
            if(authors.isEmpty()){
                Status status = new Status("Authors details not found",false);
                response = new Gson().toJson(status);
            }
            return response;
        }catch(Exception e){
            logger.error("Exception while fetching the authors details : "+e.getMessage());
            Status status = new Status("Something went wrong while fetching the authors details",false);
            response = new Gson().toJson(status);
            return response;
        }
    }

    @DeleteMapping("/deleteauthordetails/{athorId}")
    public String deleteAuthorDetails(@PathVariable Integer athorId){
        try{
            authorService.deleteAuthorDetails(athorId);
        }catch(Exception e){
            logger.info("Exception while deleting the author detials :: "+e.getMessage());
        }
        return "Author Id "+athorId+" details are deleted successfully";
    }

    @PutMapping("/updateauthordetails")
    public Author updateAuthorDetails(@RequestBody Author author){
        Author auth = new Author();
        try{
            auth = authorService.updateAuthorDetails(author);

        }catch(Exception e){
            logger.info("Exception while updating book details :: "+e.getMessage());
        }
        return auth;
    }
}
