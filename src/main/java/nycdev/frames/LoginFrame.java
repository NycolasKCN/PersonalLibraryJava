package nycdev.frames;

import nycdev.PersonalLibrary;
import nycdev.models.User;
import nycdev.service.AuthenticationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame{
  private final PersonalLibrary personalLibrary;
  private JFrame frame;
  private JTextField loginField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JButton registerButton;
  private JLabel passwordLabel;
  private JLabel loginLabel;


  public LoginFrame(PersonalLibrary personalLibrary) {
    this.personalLibrary = personalLibrary;
    configFrame();
    configComponents();
    configLayout();
  }

  public void configFrame() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int resp = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close?");
        if (resp == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
    frame.setTitle("Login");
    frame.setSize(400, 200);
    frame.setLocation(400, 250);
    frame.setResizable(false);
    frame.setBackground(Color.lightGray);
  }

  public void configComponents() {
    loginLabel = new JLabel("Login : ", JLabel.CENTER);
    loginLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    passwordLabel = new JLabel("Password: ", JLabel.CENTER);
    passwordLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));

    loginField = new JTextField();
    passwordField = new JPasswordField();

    loginButton = new JButton("Login");
    loginButton.addActionListener((e) -> {
      try {
        String login = loginField.getText();
        String password = String.valueOf(passwordField.getPassword());
        User loggedUser = personalLibrary.getWebService().authenticateUser(login, password);
        personalLibrary.setLoggedUser(loggedUser);
        personalLibrary.changeToMenu();
        cleanInputs();
      } catch (AuthenticationException ex) {
        JOptionPane.showMessageDialog(frame, "Login or password is incorrect.");
      }
    });
    registerButton = new JButton("Create account");
    registerButton.addActionListener((e) -> {
      personalLibrary.changeToRegister();
    });
  }

  private void configLayout() {
    GridLayout frameLayout = new GridLayout(3,2);
    frameLayout.setHgap(5);
    frameLayout.setVgap(20);
    frame.setLayout(frameLayout);
    frame.add(loginLabel);
    frame.add(loginField);
    frame.add(passwordLabel);
    frame.add(passwordField);
    frame.add(registerButton);
    frame.add(loginButton);
  }

  private void cleanInputs() {
    loginField.setText("");
    passwordField.setText("");
  }


  public void setVisible(boolean b) {
    frame.setVisible(b);
  }

  public JFrame getFrame() {
    return frame;
  }
}
