package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.models.book.Book;
import pl.edu.pwr.ztw.books.models.book.FormBook;
import pl.edu.pwr.ztw.books.models.rental.FormRental;
import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.services.book.IBooksService;
import pl.edu.pwr.ztw.books.services.rentals.IRentalsService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

@RestController
public class RentalsController {
    IRentalsService rentalsService;

    @Autowired
    public RentalsController(IRentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @GetMapping("/get/rentals")
    public ResponseEntity<Collection<Rental>> getRentals(){
        return new ResponseEntity<>(rentalsService.getRentals(), HttpStatus.OK);
    }

    @GetMapping("/get/rental/{id}")
    public ResponseEntity<Optional<Rental>> getRentalById(@PathVariable("id") int id){
        return new ResponseEntity<>(rentalsService.getRentalById(id), HttpStatus.OK);
    }

    @PostMapping("rentals")
    public ResponseEntity<Object> createRental(@RequestBody FormRental rental) {
        var returnCode = rentalsService.createRental(rental);
        return ResponseGenerator.getResponseEntity(returnCode, "Rental was successfully added");
    }

    @PutMapping("/rentals/{id}/{newDeadline}")
    public ResponseEntity<Object> updateRentalDeadline(@PathVariable("id") int id, @PathVariable("newDeadline") LocalDate newDeadline) {
        var returnCode = rentalsService.updateRentalDeadline(id, newDeadline);
        return ResponseGenerator.getResponseEntity(returnCode, "Rental deadline was successfully updated");
    }
    @DeleteMapping(value = "/rentals/{id}")
    public ResponseEntity<Object> deleteRental(@PathVariable("id") int id) {
        var returnCode = rentalsService.deleteRental(id);
        return ResponseGenerator.getResponseEntity(returnCode, "Rental was successfully deleted");
    }

}
