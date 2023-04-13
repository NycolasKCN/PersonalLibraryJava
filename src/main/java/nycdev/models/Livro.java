package nycdev.models;

import java.io.Serializable;
import java.util.Objects;

public class Livro implements Serializable {
    private String titulo;
    private String autor;
    private String numPag;

    public Livro(String titulo, String autor, String numPag) {
        this.titulo = titulo;
        this.autor = autor;
        this.numPag = numPag;
    }

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livro(String titulo) {
        this.titulo = titulo;
    }

    protected Livro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumPag() {
        return numPag;
    }

    public void setNumPag(String numPag) {
        this.numPag = numPag;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", numPag=" + numPag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Livro livro = (Livro) o;

        if (!Objects.equals(titulo, livro.titulo)) return false;
        return Objects.equals(autor, livro.autor);
    }

    @Override
    public int hashCode() {
        int result = titulo != null ? titulo.hashCode() : 0;
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        return result;
    }
}
