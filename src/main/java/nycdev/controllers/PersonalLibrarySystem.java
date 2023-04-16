package nycdev.controllers;

import nycdev.database.DataBase;
import nycdev.models.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonalLibrarySystem {
    private final List<Book> books;
    private final DataBase db = new DataBase();

    public PersonalLibrarySystem() {
        try {
            this.books = db.readBooks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean hasBook(Book book) {
        for (Book b : books) {
            if (b.equals(book)) {
                return true;
            }
        }
        return false;
    }

    public boolean addBook(String title, String author, String pages) {
        Book b = new Book(title, author, pages);
        if (!hasBook(b)) {
            this.books.add(new Book(title, author, pages));
            return true;
        }
        return false;
    }

    public boolean removeBook(String title, String author, String pages) {
        return books.remove(new Book(title, author, pages));
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.getName().contains(title)) {
                foundBooks.add(b);
            }
        }
        return foundBooks;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().contains(author)) {
                foundBooks.add(b);
            }
        }
        return foundBooks;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public void saveBooks() {
        try {
            db.writeBooks(books);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
