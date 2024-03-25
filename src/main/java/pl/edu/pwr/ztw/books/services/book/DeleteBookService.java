package pl.edu.pwr.ztw.books.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.edu.pwr.ztw.books.database.MockDatabase;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.services.ReturnCode;
import pl.edu.pwr.ztw.books.services.rentals.IDeleteRentalService;
import pl.edu.pwr.ztw.books.services.rentals.IGetRentalService;

import java.util.Map;

@Service("baseDeleteBookService")
public class DeleteBookService implements IDeleteBookService {
    private final static Map<Integer, Book> books = MockDatabase.getBooks();
    private final IDeleteRentalService deleteRentalService;
    private final IGetRentalService getRentalService;

    @Autowired
    public DeleteBookService(@Qualifier("baseDeleteRentalService") IDeleteRentalService deleteRentalService, @Qualifier("baseGetRentalService") IGetRentalService getRentalService) {
        this.deleteRentalService = deleteRentalService;
        this.getRentalService = getRentalService;
    }

    @Override
    public ReturnCode deleteBook(int id) {
        var deletedBook = books.remove(id);
        if (deletedBook != null) {
            for (Rental rental : getRentalService.getRentals()) {
                if (rental.getRentedBook().getId() == id)
                    deleteRentalService.deleteRental(rental.getId());
            };
            return ReturnCode.SUCCESS;
        }
        else return ReturnCode.BOOK_NOT_FOUND;
    }
}
