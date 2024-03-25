package pl.edu.pwr.ztw.books.services;

public enum RentalReturnCode implements IMessageReturnCode {
    SUCCESS,
    RENTAL_NOT_FOUND,
    NON_EXISTENT_RENTAL_BOOK,
    BOOK_CURRENTLY_RENTED,
    INVALID_DEADLINE;

    @Override
    public boolean isSuccessCode() {
        return this == SUCCESS;
    }

    public String getCodeMessage() {
        switch (this) {
            case SUCCESS -> {
                return "Operation was successful";
            }
            case RENTAL_NOT_FOUND -> {
                return "Rental with provided id was not found";
            }
            case NON_EXISTENT_RENTAL_BOOK -> {
                return "Can't rent this book, as it doesn't exist";
            }
            case BOOK_CURRENTLY_RENTED -> {
                return "Can't rent this book, as it is currently rented";
            }
            case INVALID_DEADLINE -> {
                return "Provided new deadline was not valid";
            }
        }
        return "Unknown rental error";
    }
}
