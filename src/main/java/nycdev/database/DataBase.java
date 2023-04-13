package nycdev.database;

import nycdev.models.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final String pathBooks = "src/main/resources/db/livros.dat";

    public void writeBooks(List<Book> books) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathBooks))) {
            out.writeObject(books);
        } catch (FileNotFoundException e) {
            throw new IOException("Arquivo 'Livros.dat' não foi encontrado.");
        }
    }

    public List<Book> readBooks() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathBooks))) {
            return (ArrayList<Book>) in.readObject();
        } catch (FileNotFoundException e) {
            throw new IOException("Arquivo 'Livros.dat' não foi encontrado.");
        } catch (ClassNotFoundException e) {
            throw new IOException("Classe dos objetos gravados não existe.");
        }
    }

}
