package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.author.FormAuthor;
import pl.edu.pwr.ztw.books.services.author.AuthorsService;

import java.util.Collection;
import java.util.Optional;

@RestController
public class AuthorsController {
    AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/get/authors")
    public ResponseEntity<Collection<Author>> getAuthors(){
        return new ResponseEntity<>(authorsService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("/get/authors/{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable("id") int id){
        var author = authorsService.getAuthorById(id);
        return ResponseGenerator.getResponseEntityForOptional(author, "Couldn't find an author with id " + id);
    }

    @PostMapping("authors")
    public ResponseEntity<Object> createAuthor(@RequestBody FormAuthor author) {
        var returnCode = authorsService.createAuthor(author);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Author was successfully added", HttpStatus.CREATED);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, @RequestBody FormAuthor author) {
        var returnCode = authorsService.updateAuthor(id, author);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Author was successfully updated", HttpStatus.OK);
    }
    @DeleteMapping(value = "/authors/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id) {
        var returnCode = authorsService.deleteAuthor(id);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Author was successfully deleted", HttpStatus.OK);
    }



}
