package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.book.FormBook;
import pl.edu.pwr.ztw.books.services.book.IBooksService;

import java.util.Collection;

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

    @GetMapping("/get/books/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") int id){
        var book = booksService.getBookById(id);
        return ResponseGenerator.getResponseEntityForOptional(book, "Couldn't find a book with id " + id);
    }

    @PostMapping("books")
    public ResponseEntity<Object> createBook(@RequestBody FormBook book) {
        var returnCode = booksService.createBook(book);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Book was successfully added", HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody FormBook book) {
        var returnCode = booksService.updateBook(id, book);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Book was successfully updated", HttpStatus.OK);
    }
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        var returnCode = booksService.deleteBook(id);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Book was successfully deleted", HttpStatus.OK);
    }

}
