package pl.edu.pwr.ztw.books.services.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.book.FormBook;
import pl.edu.pwr.ztw.books.services.author.AuthorsService;
import pl.edu.pwr.ztw.books.services.author.IAuthorsService;
import pl.edu.pwr.ztw.books.services.ReturnCode;
import pl.edu.pwr.ztw.books.services.authorbook.IAuthorBookService;

import java.util.*;

@Service
public class BooksService implements IBooksService {
    private final static Map<Integer, Book> books = MockDatabase.getBooks();
    private final IAuthorBookService authorBookService;
    private final IDeleteBookService deleteBookService;
    private final IGetBookService getBookService;

    @Autowired
    public BooksService(
            IDeleteBookService deleteBookService,
            IGetBookService getBookService,
            IAuthorBookService authorBookService
    ) {
        this.authorBookService = authorBookService;
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
        var author = authorBookService.getBookAuthor(book.getAuthorId());
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
            var author = authorBookService.getBookAuthor(book.getAuthorId());
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
