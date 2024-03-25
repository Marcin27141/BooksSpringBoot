package pl.edu.pwr.ztw.books.services.book;

import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.services.ReturnCode;

import java.util.Collection;
import java.util.Optional;

public interface IGetBookService {
    Collection<Book> getBooks();
    Optional<Book> getBookById(int id);
}
