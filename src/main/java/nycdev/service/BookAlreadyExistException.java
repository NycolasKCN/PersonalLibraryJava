package nycdev.service;

public class BookAlreadyExistException extends Exception {
    public BookAlreadyExistException(String s) {
        super(s);
    }
}
