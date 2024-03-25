package pl.edu.pwr.ztw.books.services.rentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.rental.FormRental;
import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.services.RentalReturnCode;
import pl.edu.pwr.ztw.books.services.ReturnCode;
import pl.edu.pwr.ztw.books.services.book.IGetBookService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class RentalService implements IRentalsService {
    private final static Map<Integer, Rental> rentals = MockDatabase.getRentals();
    private final IGetBookService getBookService;
    private final IDeleteRentalService deleteRentalService;
    private final IGetRentalService getRentalService;

    @Autowired
    public RentalService(
            @Qualifier("baseGetBookService") IGetBookService getBookService,
            @Qualifier("baseDeleteRentalService") IDeleteRentalService deleteRentalService,
            @Qualifier("baseGetRentalService") IGetRentalService getRentalService
    ) {
        this.getBookService = getBookService;
        this.deleteRentalService = deleteRentalService;
        this.getRentalService = getRentalService;
    }

    @Override
    public Collection<Rental> getRentals() {
        return getRentalService.getRentals();
    }

    @Override
    public Optional<Rental> getRentalById(int id) {
        return getRentalService.getRentalById(id);
    }

    @Override
    public RentalReturnCode createRental(FormRental rental) {
        var book = getBookService.getBookById(rental.getRentedBookId());
        if (book.isEmpty())
            return RentalReturnCode.NON_EXISTENT_RENTAL_BOOK;
        else if (isBookRented(rental.getRentedBookId()))
            return RentalReturnCode.BOOK_CURRENTLY_RENTED;
        else {
            var newRental = new Rental(MockDatabase.useRentalsCounter(), rental.getUserId(), book.get(), rental.getRentalDate(), rental.getReturnDeadline());
            rentals.put(newRental.getId(), newRental);
            return RentalReturnCode.SUCCESS;
        }
    }

    private boolean isBookRented(int bookId) {
        return getRentals().stream().anyMatch(r -> r.getRentedBook().getId() == bookId);
    }

    @Override
    public RentalReturnCode updateRentalDeadline(int id, LocalDate newDeadline) {
        var existing = getRentalById(id);
        if (existing.isEmpty())
            return RentalReturnCode.RENTAL_NOT_FOUND;
        else if (newDeadline.isBefore(existing.get().getReturnDeadline()))
            return RentalReturnCode.INVALID_DEADLINE;
        else {
            existing.get().setReturnDeadline(newDeadline);
            return RentalReturnCode.SUCCESS;
        }
    }

    @Override
    public RentalReturnCode deleteRental(int id) {
        return deleteRentalService.deleteRental(id);
    }
}
