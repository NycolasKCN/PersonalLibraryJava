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
        frame = new JFrame("Cadastrar livro");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setBackground(Color.lightGray);
        frame.setLocation(getPosition(parent, frame));
    }

    private void configComponents() {
        titleLabel = new JLabel("Nome: ", JLabel.CENTER);
        titleLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        authorLabel = new JLabel("Autor: ", JLabel.CENTER);
        authorLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        pageLabel = new JLabel("Número de páginas: ", JLabel.CENTER);
        pageLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));

        titleTF = new JTextField();
        authorTF = new JTextField();
        numPagIn = new JTextField();

        saveBt = new JButton("Salvar");
        saveBt.addActionListener(e -> {
            String titleBook = titleTF.getText();
            String authorName = authorTF.getText();
            String pages = numPagIn.getText();
            try {
                personalLibrary.getWebService().registerBook(personalLibrary.getLogedUser(), new Book(titleBook, authorName, pages));
            } catch (BookAlreadyExistException ex) {
                JOptionPane.showMessageDialog(frame, "Livro já está cadastrado!");
                cleanInputs();
                return;
            } catch (AuthenticationException ex) {
                JOptionPane.showMessageDialog(frame, "O Usuário não tem permissão para cadastrar o livro.");
                cleanInputs();
                return;
            }
            JOptionPane.showMessageDialog(frame, "Livro cadastrado com sucesso!");
            cleanInputs();
            frame.dispose();
        });
        cancelBt = new JButton("Cancelar");
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
