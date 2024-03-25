package pl.edu.pwr.ztw.books.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.pwr.ztw.books.services.IMessageReturnCode;
import pl.edu.pwr.ztw.books.services.ReturnCode;

public class ResponseGenerator {
    public static ResponseEntity<Object> getResponseEntity(IMessageReturnCode code, String successMessage) {
        return code.isSuccessCode() ?
                new ResponseEntity<>(successMessage, HttpStatus.OK) :
                new ResponseEntity<>(code.getCodeMessage(), HttpStatus.BAD_REQUEST);
    }
}
