package pl.edu.pwr.ztw.books.services.author;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.author.FormAuthor;
import pl.edu.pwr.ztw.books.services.ReturnCode;
import pl.edu.pwr.ztw.books.services.authorbook.IAuthorBookService;

import java.util.*;

@Service
public class AuthorsService implements IAuthorsService {
    private final static Map<Integer, Author> authors = MockDatabase.getAuthors();
    private final IAuthorBookService authorBookService;
    private final IGetAuthorService authorGetService;

    @Autowired
    public AuthorsService(@Qualifier("base") IGetAuthorService authorGetService, IAuthorBookService authorBookService) {
        this.authorBookService = authorBookService;
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
        var newAuthor = new Author(MockDatabase.useAuthorsCounter(), author.firstName, author.lastName, author.birthday);
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
        var deleted = authors.remove(id);
        if (deleted != null) {
            authorBookService.handleDeletedAuthorBooks(id);
            return ReturnCode.SUCCESS;
        }
        else return ReturnCode.AUTHOR_NOT_FOUND;
    }
}
