package pl.edu.pwr.ztw.books.services.book;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.book.FormBook;
import pl.edu.pwr.ztw.books.services.ReturnCode;
import pl.edu.pwr.ztw.books.services.authorbook.IAuthorBookService;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class GetBookService implements IGetBookService {
    private final static Map<Integer, Book> books = MockDatabase.getBooks();

    @Override
    public Collection<Book> getBooks() {
        return books.values();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return Optional.ofNullable(books.get(id));
    }
}
