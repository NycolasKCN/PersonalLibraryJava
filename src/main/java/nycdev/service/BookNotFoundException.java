package nycdev.service;

public class BookNotFoundException extends Exception {
  public BookNotFoundException(String msg) {
    super(msg);
  }
}
