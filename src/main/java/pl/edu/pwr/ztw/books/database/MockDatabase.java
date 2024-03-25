package pl.edu.pwr.ztw.books.database;

import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.book.Book;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockDatabase {
    private final static Map<Integer, Author> authors = new ConcurrentHashMap<>();
    private final static Map<Integer, Book> books = new ConcurrentHashMap<>();
    private static int authorsCounter = 0;
    private static int booksCounter = 0;
    static {
        var sienkiewicz = new Author(authorsCounter++,"Henryk", "Sienkiewicz", new GregorianCalendar(1846, Calendar.MAY, 5).toZonedDateTime().toLocalDate());
        var reymont = new Author(authorsCounter++,"Stanisław", "Reymont", new GregorianCalendar(1867, Calendar.MAY, 7).toZonedDateTime().toLocalDate());
        var mickiewicz = new Author(authorsCounter++,"Adam", "Mickiewicz", new GregorianCalendar(1798, Calendar.DECEMBER, 24).toZonedDateTime().toLocalDate());
        authors.put(sienkiewicz.getId(), sienkiewicz);
        authors.put(reymont.getId(), reymont);
        authors.put(mickiewicz.getId(), mickiewicz);

        var potop = new Book(booksCounter++,"Potop", sienkiewicz, 936);
        var wesele = new Book(booksCounter++,"Wesele", reymont, 150);
        var dziady = new Book(booksCounter++,"Dziady", mickiewicz, 292);
        books.put(potop.getId(), potop);
        books.put(wesele.getId(), wesele);
        books.put(dziady.getId(), dziady);
    }

    public static Map<Integer, Author> getAuthors() {
        return authors;
    }

    public static Map<Integer, Book> getBooks() {
        return books;
    }

    public static int useAuthorsCounter() {
        return authorsCounter++;
    }

    public static int useBooksCounter() {
        return booksCounter++;
    }
}
