package pl.edu.pwr.ztw.books.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.edu.pwr.ztw.books.services.ReturnCode;

public class ResponseGenerator {
    public static ResponseEntity<Object> getResponseEntity(ReturnCode code, String successMessage) {
        return code == ReturnCode.SUCCESS ?
                new ResponseEntity<>(successMessage, HttpStatus.OK) :
                new ResponseEntity<>(code.getCodeMessage(), HttpStatus.BAD_REQUEST);
    }
}
