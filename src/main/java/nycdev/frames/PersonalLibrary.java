package nycdev.frames;

import nycdev.SistemaLivro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PersonalLibrary extends JFrame {

    JLabel title, image;
    JMenuBar menuBar = new JMenuBar();
    JFrame cadastraFrame;
    ImageIcon reiImg = new ImageIcon("src/main/resources/assets/reiImg.png");

    SistemaLivro sistema;


    public PersonalLibrary() {
        sistema = new SistemaLivro();
        JFrame f = this;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(f, "Tem certeza de fechar?");
                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });


        setSize(800,600);
        setLocation(300, 50);
        setResizable(false);
        setTitle("PersonalLibrary");
        setBackground(Color.lightGray);

        cadastraFrame = new CadastrarLivroFrame(sistema);

        title = new JLabel("Bem vindo, User", JLabel.CENTER);
        title.setFont(new Font("serif", Font.BOLD, 28));
        image = new JLabel(reiImg, JLabel.CENTER);

        JMenu cadastrarMenu = new JMenu("Cadastrar");
        JMenuItem cadastraLivro = new JMenuItem("Cadastar novo livro");
        cadastraLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastraFrame.setVisible(true);
            }
        });
        cadastrarMenu.add(cadastraLivro);

        JMenu pesquisaMenu = new JMenu("Pesquisar");
        JMenuItem pesquisaLivro = new JMenuItem("Pesquisar livro");
        pesquisaMenu.add(pesquisaLivro);
        JMenu excluirMenu = new JMenu("Excluir");
        JMenuItem excluirLivro = new JMenuItem("Excluir livro");
        excluirMenu.add(excluirLivro);

        menuBar.add(cadastrarMenu);
        menuBar.add(pesquisaMenu);
        menuBar.add(excluirMenu);

        setJMenuBar(menuBar);
        setLayout(new GridLayout(1,2));
        add(title);
        add(image);

    }

    public static void main(String[] args) {
        JFrame f = new PersonalLibrary();
        f.setVisible(true);
    }
}
