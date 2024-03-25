package pl.edu.pwr.ztw.books.models.book;

public class FormBook {
    private String title;
    private int authorId;
    int pages;

    public FormBook(String title, int authorId, int pages) {
        this.title = title;
        this.authorId = authorId;
        this.pages = pages;
    }

    public void mapToBook(Book book) {
        book.setTitle(title);
        book.setPages(pages);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
