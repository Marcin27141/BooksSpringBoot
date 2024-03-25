package pl.edu.pwr.ztw.books.services;

import pl.edu.pwr.ztw.books.models.Book;

import java.util.Collection;
import java.util.Optional;

public interface IBooksService {
    Collection<Book> getBooks();
    Optional<Book> getBookById(int id);
    void createBook(Book book);
    boolean updateBook(int id, Book book);
    boolean deleteBook(int id);
}
