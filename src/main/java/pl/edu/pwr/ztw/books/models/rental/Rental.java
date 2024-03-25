package pl.edu.pwr.ztw.books.models.rental;

import pl.edu.pwr.ztw.books.models.book.Book;

import java.time.LocalDate;
import java.util.UUID;

public class Rental {
    private int id;
    private UUID userId;
    private Book rentedBook;
    private LocalDate rentalDate;
    private LocalDate returnDeadline;

    public Rental(int id, UUID userId, Book rentedBook, LocalDate rentalDate, LocalDate returnDeadline) {
        this.id = id;
        this.userId = userId;
        this.rentedBook = rentedBook;
        this.rentalDate = rentalDate;
        this.returnDeadline = returnDeadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Book getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(Book rentedBook) {
        this.rentedBook = rentedBook;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDeadline() {
        return returnDeadline;
    }

    public void setReturnDeadline(LocalDate returnDeadline) {
        this.returnDeadline = returnDeadline;
    }
}
