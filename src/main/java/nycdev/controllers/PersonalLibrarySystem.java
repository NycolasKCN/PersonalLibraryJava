package nycdev.controllers;

import nycdev.database.DataBase;
import nycdev.models.Book;

import java.io.IOException;
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

    public void cadastraLivro(String nome, String autor, String numPag ) {
        this.books.add(new Book(nome, autor, numPag));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void salvarDados() {
        try {
            db.writeBooks(books);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}
