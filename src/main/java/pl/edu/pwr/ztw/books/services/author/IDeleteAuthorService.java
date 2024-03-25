package pl.edu.pwr.ztw.books.services.author;

import pl.edu.pwr.ztw.books.models.author.FormAuthor;
import pl.edu.pwr.ztw.books.services.ReturnCode;

public interface IDeleteAuthorService {
    ReturnCode deleteAuthor(int id);
}
