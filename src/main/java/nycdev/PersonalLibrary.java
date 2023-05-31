package nycdev;

import nycdev.frames.LoginFrame;
import nycdev.frames.MenuFrame;
import nycdev.service.BookService;
import nycdev.service.LoginService;

public class PersonalLibrary {
    MenuFrame menuFrame;
    LoginFrame loginFrame;
    LoginService loginService;
    BookService bookService;

    public PersonalLibrary() {
        loginService = new LoginService();
        bookService = new BookService();
        menuFrame = new MenuFrame();
        loginFrame = new LoginFrame(this);
    }

    public void init() {
        loginFrame.setVisible(true);
    }

    public void changeToMenu() {
        loginFrame.setVisible(false);
        menuFrame.setVisible(true);
    }
}
