package pl.edu.pwr.ztw.books.services.rentals;

import pl.edu.pwr.ztw.books.services.RentalReturnCode;

public interface IDeleteRentalService {
    RentalReturnCode deleteRental(int id);
}
