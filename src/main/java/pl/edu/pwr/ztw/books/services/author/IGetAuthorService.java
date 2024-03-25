package pl.edu.pwr.ztw.books.services.author;

import pl.edu.pwr.ztw.books.models.author.Author;

import java.util.Collection;
import java.util.Optional;

public interface IGetAuthorService {
    Collection<Author> getAuthors();
    Optional<Author> getAuthorById(int id);
}
