package nycdev.frames;

import nycdev.controllers.PersonalLibrarySystem;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nycolas Kevin
 */
public class RegisterBookFrame {
    JFrame frame, parent;
    JLabel titleLabel, authorLabel, pageLabel;
    JTextField titleTF, authorTF, numPagIn;
    JButton saveBt, cancelBt;
    PersonalLibrarySystem sys;

    ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_add_circle_outline_black_48dp.png");

    public RegisterBookFrame(JFrame parent, PersonalLibrarySystem sys) {
        this.sys = sys;
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
        frame.setLocation(getPosition());
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
            boolean confirm = sys.addBook(titleBook, authorName, pages);
            if (confirm) {
                JOptionPane.showMessageDialog(frame, "Livro cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Livro já está cadastrado!");
            }
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

    private Point getParentLocation() {
        if (this.parent == null) {
            return new Point(0, 0);
        }
        return this.parent.getLocationOnScreen();
    }

    private Point getPosition() {
        Point posParent = getParentLocation();
        Dimension sizeParent = parent.getSize();
        Dimension sizeThis = frame.getSize();
        int x = (int) (posParent.getX() + (sizeParent.getWidth() / 2) - (sizeThis.getWidth() / 2));
        int y = (int) (posParent.getY() + (sizeParent.getHeight() / 2) - (sizeThis.getHeight() / 2));
        return new Point(x, y);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
