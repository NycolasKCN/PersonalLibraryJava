package nycdev.models;

import nycdev.dto.BookDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Nycolas Kevin
 */
public class Book implements Serializable {

  private Long id;
  private String title;
  private String author;
  private String pages;

  public Book(String title, String author, String pages) {
    this.title = title;
    this.author = author;
    this.pages = pages;
  }

  public Book(BookDto bookDto) {
    this.id = bookDto.getId();
    this.title = bookDto.getName();
    this.author = bookDto.getAuthor();
    this.pages = bookDto.getPages();
  }

  protected Book() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return title;
  }

  public void setName(String name) {
    this.title = name;
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
            "titulo='" + title + '\'' +
            ", autor='" + author + '\'' +
            ", paginas=" + pages +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Book book = (Book) o;

    if (!Objects.equals(title, book.title)) return false;
    return Objects.equals(author, book.author);
  }

  @Override
  public int hashCode() {
    int result = title != null ? title.hashCode() : 0;
    result = 31 * result + (author != null ? author.hashCode() : 0);
    return result;
  }
}
