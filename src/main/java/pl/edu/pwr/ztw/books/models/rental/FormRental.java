package pl.edu.pwr.ztw.books.models.rental;

import pl.edu.pwr.ztw.books.models.book.Book;

import java.time.LocalDate;
import java.util.UUID;

public class FormRental {
    private UUID userId;
    private int rentedBookId;
    private LocalDate rentalDate;
    private LocalDate returnDeadline;

    public FormRental(UUID userId, int rentedBookId, LocalDate rentalDate, LocalDate returnDeadline) {
        this.userId = userId;
        this.rentedBookId = rentedBookId;
        this.rentalDate = rentalDate;
        this.returnDeadline = returnDeadline;
    }

    public int getRentedBookId() {
        return rentedBookId;
    }

    public void setRentedBookId(int rentedBookId) {
        this.rentedBookId = rentedBookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
