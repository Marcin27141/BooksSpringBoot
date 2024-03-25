package pl.edu.pwr.ztw.books.services.rentals;

import pl.edu.pwr.ztw.books.models.rental.FormRental;
import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.services.RentalReturnCode;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface IRentalsService extends IGetRentalService, IDeleteRentalService {
    RentalReturnCode createRental(FormRental rental);
    RentalReturnCode updateRentalDeadline(int id, LocalDate newDeadline);
}
