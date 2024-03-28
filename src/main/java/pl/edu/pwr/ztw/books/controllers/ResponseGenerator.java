package pl.edu.pwr.ztw.books.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import pl.edu.pwr.ztw.books.services.IMessageReturnCode;

import java.util.Optional;

public class ResponseGenerator {
    public static ResponseEntity<Object> getResponseEntityForCode(IMessageReturnCode code, String successMessage, HttpStatus successStatus) {
        return code.isSuccessCode() ?
                new ResponseEntity<>(successMessage, successStatus) :
                new ResponseEntity<>(new JsonError(code.getCodeMessage()), HttpStatus.BAD_REQUEST);
    }

    public static <T> ResponseEntity<Object> getResponseEntityForOptional(Optional<T> optionalReturn, String errorMessage) {
        return optionalReturn.isPresent() ?
                new ResponseEntity<>(optionalReturn.get(), HttpStatus.OK) :
                getErrorResponseEntity(HttpStatus.NOT_FOUND, errorMessage);
    }

    public static ResponseEntity<Object> getErrorResponseEntity(HttpStatusCode errorCode, String errorMessage) {
        return new ResponseEntity<>(new JsonError(errorMessage), errorCode);
    }

    public record JsonError(String errorMessage) {}
}
