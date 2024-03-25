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
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable("id") int id){
        return new ResponseEntity<>(authorsService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping("authors")
    public ResponseEntity<Object> createAuthor(@RequestBody FormAuthor author) {
        var returnCode = authorsService.createAuthor(author);
        return ResponseGenerator.getResponseEntity(returnCode, "Author was successfully added");
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, @RequestBody FormAuthor author) {
        var returnCode = authorsService.updateAuthor(id, author);
        return ResponseGenerator.getResponseEntity(returnCode, "Author was successfully updated");
    }
    @DeleteMapping(value = "/authors/{id}")
    public ResponseEntity<Object> deleteAuthor(@PathVariable("id") int id) {
        var returnCode = authorsService.deleteAuthor(id);
        return ResponseGenerator.getResponseEntity(returnCode, "Author was successfully deleted");
    }



}
