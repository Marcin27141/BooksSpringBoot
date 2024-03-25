package pl.edu.pwr.ztw.books.services.authorbook;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.services.author.IGetAuthorService;
import pl.edu.pwr.ztw.books.services.book.BooksService;
import pl.edu.pwr.ztw.books.services.book.IDeleteBookService;
import pl.edu.pwr.ztw.books.services.book.IGetBookService;

import java.util.Optional;

@Service
public class AuthorBookService implements IAuthorBookService {

    private final IGetAuthorService authorGetService;
    private final IDeleteBookService deleteBookService;
    private final IGetBookService getBookService;

    public AuthorBookService(
            @Qualifier("base") IGetAuthorService authorGetService,
            IGetBookService getBookService,
            IDeleteBookService deleteBookService
    ) {
        this.authorGetService = authorGetService;
        this.deleteBookService = deleteBookService;
        this.getBookService = getBookService;
    }

    @Override
    public Optional<Author> getBookAuthor(int authorId) {
        return authorGetService.getAuthorById(authorId);
    }

    @Override
    public void handleDeletedAuthorBooks(int authorId) {
        for(Book book : getBookService.getBooks()) {
            if (book.getAuthor().id == authorId)
                deleteBookService.deleteBook(book.getId());
        }
    }
}
