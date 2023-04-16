package nycdev.frames;

import nycdev.controllers.PersonalLibrarySystem;

import javax.swing.*;
import java.awt.*;

public class RegisterBookFrame {
    JFrame frame, parent;
    JLabel titleLabel, authorLabel, pageLabel;
    JTextField titleTF, authorTF, numPagIn;
    JButton saveBt, cancelBt;
    PersonalLibrarySystem sys;

    public RegisterBookFrame(JFrame parent, PersonalLibrarySystem sys) {
        this.sys = sys;
        this.parent = parent;
        configFrame();
        configLabels();
        configFields();
        configButtons();

        frame.add(titleLabel);
        frame.add(titleTF);
        frame.add(authorLabel);
        frame.add(authorTF);
        frame.add(pageLabel);
        frame.add(numPagIn);
        frame.add(cancelBt);
        frame.add(saveBt);
    }

    private void configFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setTitle("Cadastrar livro");
        frame.setSize(400, 200);
        frame.setResizable(false);
        frame.setBackground(Color.lightGray);
        frame.getContentPane().setLayout(new GridLayout(4, 2));
        Point pos = getMidOfParent();
        frame.setLocation(pos);
    }

    private void configLabels() {
        titleLabel = new JLabel("Nome: ", JLabel.CENTER);
        titleLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        authorLabel = new JLabel("Autor: ", JLabel.CENTER);
        authorLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        pageLabel = new JLabel("Número de páginas: ", JLabel.CENTER);
        pageLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    }

    private void configFields() {
        titleTF = new JTextField();
        authorTF = new JTextField();
        numPagIn = new JTextField();
    }

    private void configButtons() {
        saveBt = new JButton("Salvar");
        saveBt.addActionListener(e -> {
            String titleBook = titleTF.getText();
            String authorName = authorTF.getText();
            String pages = numPagIn.getText();
            sys.addBook(titleBook, authorName, pages);
            JOptionPane.showMessageDialog(frame, "Livro cadastrado com sucesso!");
            frame.dispose();
        });
        cancelBt = new JButton("Cancelar");
        cancelBt.addActionListener(e -> {
            frame.dispose();
        });
    }

    private Point getParentLocation() {
        if (this.parent == null) {
            return new Point(0, 0);
        }
        return this.parent.getLocationOnScreen();
    }

    private Point getMidOfParent() {
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
