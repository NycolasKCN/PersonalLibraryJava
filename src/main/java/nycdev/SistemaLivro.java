package nycdev;

import nycdev.database.DataBase;
import nycdev.models.Livro;
import nycdev.models.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SistemaLivro {
    private List<Livro> livros;
    private List<Usuario> users;
    private final DataBase db = new DataBase();

    public SistemaLivro() {
        try {
            this.livros = db.leLivros();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.users = new ArrayList<>();
    }

    public void cadastraLivro(String nome, String autor, String numPag ) {
        this.livros.add(new Livro(nome, autor, numPag));
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void salvarDados() {
        try {
            db.gravarLivros(livros);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}
