package nycdev.frames;

import nycdev.PersonalLibrary;
import nycdev.service.UserAlreadyExistException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterFrame {
  private final PersonalLibrary personalLibrary;
  private JFrame frame;
  private JLabel nameLabel;
  private JTextField nameField;
  private JLabel loginLabel;
  private JTextField loginField;
  private JLabel passwordLabel;
  private JPasswordField passwordField;
  private JButton backButton;
  private JButton registerButton;

  public RegisterFrame(PersonalLibrary personalLibrary) {
    this.personalLibrary = personalLibrary;
    configFrame();
    configComponents();
    configLayout();
  }

  public void configFrame() {
    frame = new JFrame();
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
    frame.setTitle("Create account");
    frame.setSize(400, 200);
    frame.setResizable(false);
    frame.setLocation(300, 250);
  }

  public void configComponents() {
    nameLabel = new JLabel("Name: ", JLabel.CENTER);
    loginLabel = new JLabel("Login: ", JLabel.CENTER);
    passwordLabel =  new JLabel("Password: ", JLabel.CENTER);
    nameLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    loginLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    passwordLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));

    nameField = new JTextField();
    loginField = new JTextField();
    passwordField = new JPasswordField();

    registerButton = new JButton("Create account");
    registerButton.addActionListener((e) -> {
      String name = nameField.getText();
      String login = loginField.getText();
      String password = String.valueOf(passwordField.getPassword());

      try {
        // TODO: 31/05/2023 Verificar esses dados de entrada. 
        personalLibrary.getWebService().registerUser(name, login, password);
        JOptionPane.showMessageDialog(frame, "Account created successfully");
      } catch (UserAlreadyExistException ex) {
        JOptionPane.showMessageDialog(frame, "Account with this login already exists! try another.");
      }
    });
    backButton = new JButton("Back to login");
    backButton.addActionListener((e) -> {
      personalLibrary.changeToLogin();
    });
  }

  private void configLayout() {
    JPanel content = new JPanel();
    content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    GridLayout layout = new GridLayout(4, 2, 5, 5);
    content.setLayout(layout);
    content.add(nameLabel);
    content.add(nameField);
    content.add(loginLabel);
    content.add(loginField);
    content.add(passwordLabel);
    content.add(passwordField);
    content.add(backButton);
    content.add(registerButton);

    frame.setContentPane(content);
  }

  private void cleanInputs() {
    nameField.setText("");
    loginField.setText("");
    passwordField.setText("");
  }

  public void setVisible(boolean visible) {
    frame.setVisible(visible);
  }
}
