package com.galvanize.tmo.paspringstarter;

import com.galvanize.tmo.paspringstarter.model.Books;
import com.galvanize.tmo.paspringstarter.model.Data;
import com.galvanize.tmo.paspringstarter.service.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {

    @Autowired
    public BusinessLogic businessLogic;

    @GetMapping("/health")
    public void health() {

    }

    @RequestMapping(value = "/api/books",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<Books> addBook(@RequestBody Data data) {
        Books books = new Books();
       books =  businessLogic.addBooks(data);
        return new ResponseEntity<>(books,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/books",
    method = RequestMethod.GET,
    consumes = "application/json",
    produces = "application/json")
    public ResponseEntity<Books> getBooks() {
        Books books = businessLogic.getBooks();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }


    @RequestMapping(value = "/books",
    method = RequestMethod.DELETE,
    consumes = "application/json",
    produces = "application/json")
    public ResponseEntity<String> deleteBooks() {
        businessLogic.deleteBooks();
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
