package nycdev.frames;

import nycdev.SistemaLivro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PersonalLibrary {
    JFrame mainFrame;
    JFrame cadastraFrame;
    JMenuBar menuBar = new JMenuBar();
    JLabel title, image;
    ImageIcon reiImg = new ImageIcon("src/main/resources/assets/reiImg.png");

    SistemaLivro sistema;

    public PersonalLibrary() {
        sistema = new SistemaLivro();
        cadastraFrame = new CadastrarLivroFrame(sistema);

        configMenuBar();
        configLabels();
        configFrame();
    }

    private void configMenuBar() {
        JMenu cadastrarMenu = new JMenu("Cadastrar");
        JMenuItem cadastraLivro = new JMenuItem("Cadastar novo livro");
        cadastraLivro.addActionListener(e -> {
            cadastraFrame.setVisible(true);
        });
        cadastrarMenu.add(cadastraLivro);

        JMenu pesquisaMenu = new JMenu("Pesquisar");
        JMenuItem pesquisaLivro = new JMenuItem("Pesquisar livro");
        pesquisaLivro.addActionListener(e -> {
            System.out.println(sistema.getLivros());
        });
        pesquisaMenu.add(pesquisaLivro);

        JMenu excluirMenu = new JMenu("Excluir");
        JMenuItem excluirLivro = new JMenuItem("Excluir livro");
        excluirMenu.add(excluirLivro);

        JMenu salvarMenu = new JMenu("Salvar");
        JMenuItem salvarDados = new JMenuItem("Salvar dados");
        salvarDados.addActionListener(e -> {
            sistema.salvarDados();
            JOptionPane.showMessageDialog(mainFrame, "Dados salvos com sucesso.");
        });
        salvarMenu.add(salvarDados);

        menuBar.add(cadastrarMenu);
        menuBar.add(pesquisaMenu);
        menuBar.add(excluirMenu);
        menuBar.add(salvarMenu);
    }

    private void configLabels() {
        title = new JLabel("Bem vindo, User", JLabel.CENTER);
        title.setFont(new Font("serif", Font.BOLD, 28));
        image = new JLabel(reiImg, JLabel.CENTER);
    }

    private void configFrame() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(mainFrame, "Tem certeza de fechar?");
                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        mainFrame.setBackground(Color.lightGray);
        mainFrame.setLocation(300, 50);
        mainFrame.setResizable(false);
        mainFrame.setSize(800, 600);
        mainFrame.setTitle("PersonalLibrary");
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.add(title);
        mainFrame.add(image);
    }

    private void configCadastraFrame() {

    }

    public void run() {
        mainFrame.setVisible(true);
    }
}
