package nycdev.models;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private String name;
    private String author;
    private String pages;

    public Book(String name, String author, String pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }

    protected Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + name + '\'' +
                ", autor='" + author + '\'' +
                ", numPag=" + pages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(name, book.name)) return false;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }
}
