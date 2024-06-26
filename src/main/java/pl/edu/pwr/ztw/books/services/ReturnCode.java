package pl.edu.pwr.ztw.books.services;

public enum ReturnCode implements IMessageReturnCode {
    SUCCESS,
    BOOK_NOT_FOUND,
    AUTHOR_NOT_FOUND;

    @Override
    public boolean isSuccessCode() {
        return this == SUCCESS;
    }

    public String getCodeMessage() {
        switch (this) {
            case SUCCESS -> {
                return "Operation was successful";
            }
            case BOOK_NOT_FOUND -> {
                return "Book with provided id was not found";
            }
            case AUTHOR_NOT_FOUND -> {
                return "Author with provided id was not found";
            }
        }
        return "Unknown error occurred";
    }
}
