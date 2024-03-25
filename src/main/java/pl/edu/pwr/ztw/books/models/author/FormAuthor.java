package pl.edu.pwr.ztw.books.models.author;

import pl.edu.pwr.ztw.books.models.book.Book;

import java.time.LocalDate;
import java.util.Date;

public class FormAuthor {
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public FormAuthor(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public void mapToAuthor(Author author) {
        author.setFirstName(firstName);
        author.setLastName(lastName);
        author.setBirthday(birthday);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
