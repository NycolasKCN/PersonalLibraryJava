package nycdev;

import nycdev.frames.LoginFrame;
import nycdev.frames.MenuFrame;
import nycdev.frames.RegisterFrame;
import nycdev.models.User;
import nycdev.service.WebService;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class PersonalLibrary {
  private final String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
  private User loggedUser;
  private final WebService webService = new WebService();
  private final MenuFrame menuFrame;
  private final LoginFrame loginFrame;
  private final RegisterFrame registerFrame;

  public PersonalLibrary() {
    setLookAndFeel();
    menuFrame = new MenuFrame(this);
    loginFrame = new LoginFrame(this);
    registerFrame = new RegisterFrame(this);
  }

  private void setLookAndFeel() {
    try {
      UIManager.setLookAndFeel(lookAndFeel);
      UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
      UIManager.put("ToggleButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

      // ways to remove it from other controls...
      UIManager.put("CheckBox.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
      UIManager.put("TabbedPane.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
      UIManager.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
      UIManager.put("Slider.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

      // figure out combobox
      UIManager.put("ComboBox.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
             UnsupportedLookAndFeelException e) {
      throw new RuntimeException(e);
    }
  }

  public void showLoginScreen() {
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

  public void changeUser() {
    this.loggedUser = null;
    changeToLogin();
  }
}
