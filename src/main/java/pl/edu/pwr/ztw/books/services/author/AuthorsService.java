package pl.edu.pwr.ztw.books.services.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.author.FormAuthor;
import pl.edu.pwr.ztw.books.services.ReturnCode;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorsService implements IAuthorsService {
    private final static Map<Integer, Author> authors = MockDatabase.getAuthors();
    private final IDeleteAuthorService deleteAuthorService;
    private final IGetAuthorService authorGetService;

    @Autowired
    public AuthorsService(@Qualifier("baseGetAuthorService") IGetAuthorService authorGetService, @Qualifier("baseDeleteAuthorService") IDeleteAuthorService deleteAuthorService) {
        this.deleteAuthorService = deleteAuthorService;
        this.authorGetService = authorGetService;
    }

    @Override
    public Collection<Author> getAuthors() {
        return authorGetService.getAuthors();
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorGetService.getAuthorById(id);
    }


    @Override
    public ReturnCode createAuthor(FormAuthor author) {
        var newAuthor = new Author(MockDatabase.useAuthorsCounter(), author.getFirstName(), author.getLastName(), author.getBirthday());
        authors.put(newAuthor.getId(), newAuthor);
        return ReturnCode.SUCCESS;
    }

    @Override
    public ReturnCode updateAuthor(int id, FormAuthor author) {
        var existing = getAuthorById(id);
        if (existing.isPresent()) {
            author.mapToAuthor(existing.get());
            return ReturnCode.SUCCESS;
        }
        return ReturnCode.AUTHOR_NOT_FOUND;
    }

    @Override
    public ReturnCode deleteAuthor(int id) {
        return deleteAuthorService.deleteAuthor(id);
    }
}
