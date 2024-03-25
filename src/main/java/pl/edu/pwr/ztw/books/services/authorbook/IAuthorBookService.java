package pl.edu.pwr.ztw.books.services.authorbook;

import pl.edu.pwr.ztw.books.models.author.Author;

import java.awt.print.Book;
import java.util.Optional;

public interface IAuthorBookService {
    Optional<Author> getBookAuthor(int authorId);
    void handleDeletedAuthorBooks(int authorId);
}
