package pl.edu.pwr.ztw.books.services.author;

import pl.edu.pwr.ztw.books.models.author.FormAuthor;
import pl.edu.pwr.ztw.books.services.ReturnCode;

public interface IAuthorsService extends IGetAuthorService {
    ReturnCode createAuthor(FormAuthor author);
    ReturnCode updateAuthor(int id, FormAuthor author);
    ReturnCode deleteAuthor(int id);
}
