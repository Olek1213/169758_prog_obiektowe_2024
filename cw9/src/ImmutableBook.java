import java.util.Objects;

public class ImmutableBook {
    private final String title;
    private final String author;
    private final int isbn;
    public ImmutableBook(String title,String author,int isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    public String getTitle() {
        return this.title;
    }
    public String getAuthor() {
        return this.author;
    }
    public int getIsbn() {
        return this.isbn;
    }

    @Override
    public String toString() {
        return "ImmutableBook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn=" + isbn +
                '}';
    }
    public boolean equals(ImmutableBook book) {
        if(this.title != book.title) {
            return false;
        }
        if(this.author != book.author) {
            return false;
        }
        if(this.isbn != book.isbn) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, isbn);
    }
}
