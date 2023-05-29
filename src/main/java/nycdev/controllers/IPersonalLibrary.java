package nycdev.controllers;

public interface IPersonalLibrary {
    boolean hasBook();
    boolean addBook(String title, String author, String pages);

}
