package nycdev.database;

import nycdev.models.Livro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final String pathLivros = "src/main/resources/db/livros.dat";

    public void gravarLivros(List<Livro> livros) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(pathLivros))) {
            out.writeObject(livros);
        } catch (FileNotFoundException e) {
            throw new IOException("Arquivo 'Livros.dat' não foi encontrado.");
        }
    }

    public List<Livro> leLivros() throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(pathLivros))) {
            return (ArrayList<Livro>) in.readObject();
        } catch (FileNotFoundException e) {
            throw new IOException("Arquivo 'Livros.dat' não foi encontrado.");
        } catch (ClassNotFoundException e) {
            throw new IOException("Classe dos objetos gravados não existe.");
        }
    }

}
