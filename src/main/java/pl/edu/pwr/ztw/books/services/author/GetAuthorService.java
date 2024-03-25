package pl.edu.pwr.ztw.books.services.author;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.author.Author;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service("baseGetAuthorService")
public class GetAuthorService implements IGetAuthorService {
    private final static Map<Integer, Author> authors = MockDatabase.getAuthors();
    @Override
    public Collection<Author> getAuthors() {
        return authors.values();
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return Optional.ofNullable(authors.get(id));
    }
}
