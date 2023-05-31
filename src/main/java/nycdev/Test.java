package nycdev;

import nycdev.models.Book;
import nycdev.models.User;
import nycdev.service.*;

import java.util.List;


public class Test {
    public static void main(String[] args) {
//        LoginService loginService = new LoginService();
//        BookService bookService = new BookService();
//        User user = new User(3L, "sla");
//        Book book = new Book("sla", "adskas","90");
//
//        try {
//            loginService.registerUser("Nycolas","znk", "12345678");
//        } catch (UserAlreadyExistException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            user = loginService.authenticateUser("znk", "12345678");
//            System.out.println(user);
//        } catch (AuthenticationException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            book = new Book("Oyasumi punpun", "Inio Asano", "213");
//            book = bookService.registerBook(user.getToken(), user.getId(), book);
//            System.out.println(book);
//        } catch (BookAlreadyExistException | AuthenticationException e) {
//            System.out.println(e.getMessage());
//        }
//
//        try {
//            List<Book> listBook = bookService.allBooksUser(user.getToken(), user.getId());
//            for (Book b : listBook) System.out.println(b);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            bookService.deleteBook(user.getToken(), book.getId());
//            System.out.println("Deletei");
//        } catch (AuthenticationException | BookNotFoundException e) {
//            System.out.println(e.getMessage());
//        }

        new PersonalLibrary().init();

    }
}
