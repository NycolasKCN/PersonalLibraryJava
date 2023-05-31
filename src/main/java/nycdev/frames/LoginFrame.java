package nycdev.frames;

import nycdev.PersonalLibrary;
import nycdev.controller.LoginButtonController;

import javax.swing.*;

public class LoginFrame extends JFrame{
    private final PersonalLibrary personaLibrary;
    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel passwordLabel;
    private JLabel loginLabel;


    public LoginFrame(PersonalLibrary personalLibrary) {
        this.personaLibrary = personalLibrary;
        setContentPane(mainPanel);
        setTitle("Login");
        setSize(300, 250);
        setLocation(400, 250);
        setResizable(false);

        loginButton.addActionListener(new LoginButtonController(personalLibrary));
    }

}
