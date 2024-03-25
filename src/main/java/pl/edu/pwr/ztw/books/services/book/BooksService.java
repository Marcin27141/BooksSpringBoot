package pl.edu.pwr.ztw.books.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.book.FormBook;
import pl.edu.pwr.ztw.books.services.ReturnCode;
import pl.edu.pwr.ztw.books.services.author.IGetAuthorService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class BooksService implements IBooksService {
    private final static Map<Integer, Book> books = MockDatabase.getBooks();
    private final IGetAuthorService getAuthorService;
    private final IDeleteBookService deleteBookService;
    private final IGetBookService getBookService;

    @Autowired
    public BooksService(
            @Qualifier("baseDeleteBookService") IDeleteBookService deleteBookService,
            @Qualifier("baseGetBookService") IGetBookService getBookService,
            @Qualifier("baseGetAuthorService") IGetAuthorService getAuthorService
    ) {
        this.getAuthorService = getAuthorService;
        this.deleteBookService = deleteBookService;
        this.getBookService = getBookService;
    }

    @Override
    public Collection<Book> getBooks() {
        return getBookService.getBooks();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return getBookService.getBookById(id);
    }

    @Override
    public ReturnCode createBook(FormBook book) {
        var author = getAuthorService.getAuthorById(book.getAuthorId());
        if (author.isPresent()) {
            var newBook = new Book(MockDatabase.useBooksCounter(), book.getTitle(), author.get(), book.getPages());
            books.put(newBook.getId(), newBook);
            return ReturnCode.SUCCESS;
        }
        return ReturnCode.AUTHOR_NOT_FOUND;
    }

    @Override
    public ReturnCode updateBook(int id, FormBook book) {
        var existing = getBookById(id);
        if (existing.isPresent()) {
            var existingBook = existing.get();
            var author = getAuthorService.getAuthorById(book.getAuthorId());
            if (author.isPresent()) {
                book.mapToBook(existingBook);
                existingBook.setAuthor(author.get());
            } else return ReturnCode.AUTHOR_NOT_FOUND;
            return ReturnCode.SUCCESS;
        }
        return ReturnCode.BOOK_NOT_FOUND;
    }

    @Override
    public ReturnCode deleteBook(int id) {
        return deleteBookService.deleteBook(id);
    }
}
