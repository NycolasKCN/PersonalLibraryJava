package nycdev.controllers;

import nycdev.database.DataBase;
import nycdev.models.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nycolas Kevin
 */
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

    public boolean addBook(Book book) {
        if (!hasBook(book)) {
            this.books.add(book);
            return true;
        }
        return false;
    }

    public boolean removeBook(String title, String author) {
        return books.remove(new Book(title, author, " "));
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

    private static void populateDataBase(PersonalLibrarySystem sys) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/db/livros.txt"))){
            String linha;
            while ((linha = br.readLine()) != null) { // lê cada linha do arquivo
                String[] dadosLivro = linha.split(","); // divide a linha em três partes: título, autor e páginas
                String titulo = dadosLivro[0].trim();
                String autor = dadosLivro[1].trim();
                String paginas = dadosLivro[2].trim();

                sys.addBook(new Book(titulo, autor, paginas));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        PersonalLibrarySystem sys = new PersonalLibrarySystem();
        PersonalLibrarySystem.populateDataBase(sys);
        sys.saveBooks();
    }
}
