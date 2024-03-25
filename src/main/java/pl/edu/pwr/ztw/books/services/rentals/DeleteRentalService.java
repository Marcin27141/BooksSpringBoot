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

@Service("baseDeleteRentalService")
public class DeleteRentalService implements IDeleteRentalService {
    private final static Map<Integer, Rental> rentals = MockDatabase.getRentals();
    @Override
    public RentalReturnCode deleteRental(int id) {
        return rentals.remove(id) != null ? RentalReturnCode.SUCCESS : RentalReturnCode.RENTAL_NOT_FOUND;
    }
}
