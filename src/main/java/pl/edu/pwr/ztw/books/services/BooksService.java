package pl.edu.pwr.ztw.books.services;

import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.models.Book;

import java.util.*;

@Service
public class BooksService implements IBooksService {
    private final static Map<Integer, Book> booksRepo = new HashMap<>();
    private static int booksCounter = 0;
    static {
        var potop = new Book(booksCounter++,"Potop", "Henryk Sienkiewicz", 936);
        var wesele = new Book(booksCounter++,"Wesele", "Stanisław Reymont", 150);
        var dziady = new Book(booksCounter++,"Dziady", "Adam Mickiewicz", 292);
        booksRepo.put(potop.getId(), potop);
        booksRepo.put(wesele.getId(), wesele);
        booksRepo.put(dziady.getId(), dziady);
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo.values();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return Optional.ofNullable(booksRepo.get(id));
    }

    @Override
    public void createBook(Book book) {
        book.setId(booksCounter++);
        booksRepo.put(book.getId(), book);
    }

    @Override
    public boolean updateBook(int id, Book book) {
        if (getBookById(id).isPresent()) {
            book.setId(id);
            booksRepo.put(id, book);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteBook(int id) {
        return booksRepo.remove(id) != null;
    }
}
