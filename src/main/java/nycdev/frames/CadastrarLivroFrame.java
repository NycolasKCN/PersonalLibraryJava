package nycdev.frames;

import nycdev.controllers.PersonalLibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarLivroFrame extends JFrame{
    JFrame parent;
    JLabel nome, autor, numPag;
    JTextField nomeIn, autorIn, numPagIn;
    JButton salvarButton, cancelarButton;

    PersonalLibrarySystem sis;
    public CadastrarLivroFrame(JFrame parent, PersonalLibrarySystem sis) {
        this.sis = sis;
        this.parent = parent;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setAll();
        setLabels();
        setFields();
        setButtons();

        add(nome);
        add(nomeIn);
        add(autor);
        add(autorIn);
        add(numPag);
        add(numPagIn);
        add(cancelarButton);
        add(salvarButton);
    }

    private void setAll() {
        setTitle("Cadastrar livro");
        setSize(400, 200);
        setResizable(false);
        setBackground(Color.lightGray);
        getContentPane().setLayout(new GridLayout(4,2));

        Point pos = getMidOfParent();
        setLocation(pos);
    }

    private void setLabels() {
        nome = new JLabel("Nome: ", JLabel.CENTER);
        nome.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        autor = new JLabel("Autor: ", JLabel.CENTER);
        autor.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        numPag = new JLabel("Número de páginas: ", JLabel.CENTER);
        numPag.setFont(new Font("Noto Sans", Font.PLAIN, 14));
    }

    private void setFields() {
        nomeIn = new JTextField();
        autorIn = new JTextField();
        numPagIn = new JTextField();
    }

    private void setButtons() {
        salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> {
                String nomeLivro = nomeIn.getText();
                String nomeAutor = autorIn.getText();
                String numPag = numPagIn.getText();
                sis.cadastraLivro(nomeLivro, nomeAutor, numPag);
                JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
                this.dispose();
        });
        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(e -> {
            this.dispose();
        });
    }

    private Point getParentLocation() {
        if (this.parent == null) {
            return new Point(0,0);
        }
        return this.parent.getLocationOnScreen();
    }

    private Point getMidOfParent() {
        Point posParent = getParentLocation();
        Dimension sizeParent = parent.getSize();
        Dimension sizeThis = getSize();
        int x = (int) (posParent.getX() + (sizeParent.getWidth() / 2) - (sizeThis.getWidth() / 2));
        int y = (int) (posParent.getY() + (sizeParent.getHeight() / 2) - (sizeThis.getHeight() / 2));
        return new Point(x, y);
    }

    public static void main(String[] args) {
        PersonalLibrarySystem s = new PersonalLibrarySystem();
        JFrame j = new CadastrarLivroFrame(null , s);
        j.setVisible(true);
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
