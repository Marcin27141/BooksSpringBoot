package pl.edu.pwr.ztw.books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.ztw.books.models.rental.FormRental;
import pl.edu.pwr.ztw.books.models.rental.Rental;
import pl.edu.pwr.ztw.books.services.rentals.IRentalsService;

import java.time.LocalDate;
import java.util.Collection;

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

    @GetMapping("/get/rentals/{id}")
    public ResponseEntity<Object> getRentalById(@PathVariable("id") int id){
        var rental = rentalsService.getRentalById(id);
        return ResponseGenerator.getResponseEntityForOptional(rental, "Couldn't find a rental with id " + id);
    }

    @PostMapping("rentals")
    public ResponseEntity<Object> createRental(@RequestBody FormRental rental) {
        var returnCode = rentalsService.createRental(rental);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Rental was successfully added", HttpStatus.CREATED);
    }

    @PutMapping("/rentals/{id}/{newDeadline}")
    public ResponseEntity<Object> updateRentalDeadline(@PathVariable("id") int id, @PathVariable("newDeadline") LocalDate newDeadline) {
        var returnCode = rentalsService.updateRentalDeadline(id, newDeadline);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Rental deadline was successfully updated", HttpStatus.OK);
    }
    @DeleteMapping(value = "/rentals/{id}")
    public ResponseEntity<Object> deleteRental(@PathVariable("id") int id) {
        var returnCode = rentalsService.deleteRental(id);
        return ResponseGenerator.getResponseEntityForCode(returnCode, "Rental was successfully deleted", HttpStatus.OK);
    }

}
