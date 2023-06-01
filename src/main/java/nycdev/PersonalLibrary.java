package nycdev;

import nycdev.frames.LoginFrame;
import nycdev.frames.MenuFrame;
import nycdev.frames.RegisterFrame;
import nycdev.models.User;
import nycdev.service.WebService;

public class PersonalLibrary {
    private User loggedUser;
    private final WebService webService = new WebService();
    private final MenuFrame menuFrame;
    private final LoginFrame loginFrame;
    private final RegisterFrame registerFrame;

    public PersonalLibrary() {
        menuFrame = new MenuFrame();
        loginFrame = new LoginFrame(this);
        registerFrame = new RegisterFrame(this);
    }

    public void init() {
        loginFrame.setVisible(true);
    }

    public void changeToMenu() {
        loginFrame.setVisible(false);
        menuFrame.setVisible(true);
        registerFrame.setVisible(false);
    }

    public void changeToRegister() {
        loginFrame.setVisible(false);
        menuFrame.setVisible(false);
        registerFrame.setVisible(true);
    }

    public void changeToLogin() {
        loginFrame.setVisible(true);
        menuFrame.setVisible(false);
        registerFrame.setVisible(false);
    }

    public User getLogedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User logedUser) {
        this.loggedUser = logedUser;
    }

    public WebService getWebService() {
        return webService;
    }
}
