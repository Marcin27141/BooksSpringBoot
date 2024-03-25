package pl.edu.pwr.ztw.books.database;

import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.models.author.Author;
import pl.edu.pwr.ztw.books.models.book.Book;

import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockDatabase {
    private final static Map<Integer, Author> authors = new ConcurrentHashMap<>();
    private final static Map<Integer, Book> books = new ConcurrentHashMap<>();
    private final static Map<Integer, Rental> rentals = new ConcurrentHashMap<>();
    private static int authorsCounter = 0;
    private static int booksCounter = 0;
    private static int rentalsCounter = 0;
    static {
        var sienkiewicz = new Author(authorsCounter++,"Henryk", "Sienkiewicz", LocalDate.of(1846, 5, 5));
        var reymont = new Author(authorsCounter++,"Stanisław", "Reymont", LocalDate.of(1867, 5, 7));
        var mickiewicz = new Author(authorsCounter++,"Adam", "Mickiewicz",  LocalDate.of(1798,12,24));
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
    public static Map<Integer, Rental> getRentals() {
        return rentals;
    }

    public static int useAuthorsCounter() {
        return authorsCounter++;
    }

    public static int useBooksCounter() {
        return booksCounter++;
    }
    public static int useRentalsCounter() {
        return rentalsCounter++;
    }
}
