package pl.edu.pwr.ztw.books.services.rentals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.rental.FormRental;
import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.services.RentalReturnCode;
import pl.edu.pwr.ztw.books.services.book.IGetBookService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service("baseGetRentalService")
public class GetRentalService implements IGetRentalService {
    private final static Map<Integer, Rental> rentals = MockDatabase.getRentals();

    @Override
    public Collection<Rental> getRentals() {
        return rentals.values();
    }

    @Override
    public Optional<Rental> getRentalById(int id) {
        return Optional.ofNullable(rentals.get(id));
    }
}
