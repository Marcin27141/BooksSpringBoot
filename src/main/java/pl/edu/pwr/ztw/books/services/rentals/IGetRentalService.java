package pl.edu.pwr.ztw.books.services.rentals;

import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.services.RentalReturnCode;

import java.util.Collection;
import java.util.Optional;

public interface IGetRentalService {
    Collection<Rental> getRentals();
    Optional<Rental> getRentalById(int id);
}
