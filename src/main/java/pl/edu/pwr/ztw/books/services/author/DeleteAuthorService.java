package pl.edu.pwr.ztw.books.services.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.services.ReturnCode;
import pl.edu.pwr.ztw.books.services.book.IDeleteBookService;
import pl.edu.pwr.ztw.books.services.book.IGetBookService;

import java.util.Map;

@Service("baseDeleteAuthorService")
public class DeleteAuthorService implements IDeleteAuthorService {
    private final static Map<Integer, Author> authors = MockDatabase.getAuthors();
    private final IDeleteBookService deleteBookService;
    private final IGetBookService getBookService;

    @Autowired
    public DeleteAuthorService(@Qualifier("baseGetBookService") IGetBookService getBookService,
                             @Qualifier("baseDeleteBookService") IDeleteBookService deleteBookService
    ) {
        this.deleteBookService = deleteBookService;
        this.getBookService = getBookService;
    }

    @Override
    public ReturnCode deleteAuthor(int id) {
        var deleted = authors.remove(id);
        if (deleted != null) {
            deleteAuthorBooks(id);
            return ReturnCode.SUCCESS;
        }
        else return ReturnCode.AUTHOR_NOT_FOUND;
    }

    private void deleteAuthorBooks(int authorId) {
        for(Book book : getBookService.getBooks()) {
            if (book.getAuthor().getId() == authorId)
                deleteBookService.deleteBook(book.getId());
        }
    }
}
