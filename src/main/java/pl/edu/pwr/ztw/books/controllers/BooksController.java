package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.book.FormBook;
import pl.edu.pwr.ztw.books.services.book.IBooksService;

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
    public ResponseEntity<Object> createBook(@RequestBody FormBook book) {
        var returnCode = booksService.createBook(book);
        return ResponseGenerator.getResponseEntity(returnCode, "Book was successfully added");
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") int id, @RequestBody FormBook book) {
        var returnCode = booksService.updateBook(id, book);
        return ResponseGenerator.getResponseEntity(returnCode, "Book was successfully updated");
    }
    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Object> deleteBook(@PathVariable("id") int id) {
        var returnCode = booksService.deleteBook(id);
        return ResponseGenerator.getResponseEntity(returnCode, "Product was successfully deleted");
    }

}
