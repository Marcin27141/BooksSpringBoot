package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.models.Book;
import pl.edu.pwr.ztw.books.services.IBooksService;

import java.util.Collection;
import java.util.Optional;

@RestController
public class BooksController {
    IBooksService booksService;

    @Autowired
    public BooksController(IBooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/get/books")
    public ResponseEntity<Collection<Book>> getBooks(){
        return new ResponseEntity<>(booksService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/get/book/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable("id") int id){
        return new ResponseEntity<>(booksService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping("books")
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        booksService.createBook(book);
        return new ResponseEntity<>("Book was successfully added", HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        var successfullyUpdated = booksService.updateBook(id, book);
        return successfullyUpdated ?
                new ResponseEntity<>("Book was successfully updated", HttpStatus.OK) :
                new ResponseEntity<>("There is no book with id " + id, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        var successfullyDeleted = booksService.deleteBook(id);
        return successfullyDeleted ?
                new ResponseEntity<>("Product was successfully deleted", HttpStatus.OK) :
                new ResponseEntity<>("There is no book with id " + id, HttpStatus.NOT_FOUND);
    }

}
