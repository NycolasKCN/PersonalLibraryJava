package nycdev.frames;

import nycdev.PersonalLibrary;
import nycdev.models.Book;
import nycdev.service.AuthenticationException;
import nycdev.service.BookAlreadyExistException;

import javax.swing.*;
import java.awt.*;

import static nycdev.Util.getPosition;

/**
 * @author Nycolas Kevin
 */
public class RegisterBookFrame {
    JFrame frame, parent;
    JLabel titleLabel, authorLabel, pageLabel;
    JTextField titleTF, authorTF, numPagIn;
    JButton saveBt, cancelBt;
    PersonalLibrary personalLibrary;
    ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_add_circle_outline_black_48dp.png");

    public RegisterBookFrame(JFrame parent, PersonalLibrary sys) {
        this.personalLibrary = sys;
        this.parent = parent;
        configFrame();
        configComponents();
        configLayout();
    }

    private void configFrame() {
        frame = new JFrame("New book");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setBackground(Color.lightGray);
        frame.setLocation(getPosition(parent, frame));
    }

    private void configComponents() {
        titleLabel = new JLabel("Title: ", JLabel.CENTER);
        titleLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        authorLabel = new JLabel("Author: ", JLabel.CENTER);
        authorLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        pageLabel = new JLabel("Pages: ", JLabel.CENTER);
        pageLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));

        titleTF = new JTextField();
        authorTF = new JTextField();
        numPagIn = new JTextField();

        saveBt = new JButton("Add");
        saveBt.addActionListener(e -> {
            String titleBook = titleTF.getText();
            String authorName = authorTF.getText();
            String pages = numPagIn.getText();
            try {
                personalLibrary.getWebService().registerBook(personalLibrary.getLogedUser(), new Book(titleBook, authorName, pages));
            } catch (BookAlreadyExistException ex) {
                JOptionPane.showMessageDialog(frame, "Book already registered.");
                cleanInputs();
                return;
            } catch (AuthenticationException ex) {
                JOptionPane.showMessageDialog(frame, "The user does not have permission to register the book.");
                cleanInputs();
                return;
            }
            JOptionPane.showMessageDialog(frame, "Successfully Registered Book!");
            cleanInputs();
            frame.dispose();
        });
        cancelBt = new JButton("Back");
        cancelBt.addActionListener(e -> {
            frame.dispose();
        });
    }

    private void configLayout() {
        GridLayout frameLayout = new GridLayout(4, 2);
        frameLayout.setVgap(10);
        frameLayout.setHgap(5);
        frame.setLayout(frameLayout);
        frame.add(titleLabel);
        frame.add(titleTF);
        frame.add(authorLabel);
        frame.add(authorTF);
        frame.add(pageLabel);
        frame.add(numPagIn);
        frame.add(cancelBt);
        frame.add(saveBt);
    }

    private void cleanInputs() {
        titleTF.setText("");
        authorTF.setText("");
        numPagIn.setText("");
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
