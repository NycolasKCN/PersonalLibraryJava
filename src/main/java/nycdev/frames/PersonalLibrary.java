package nycdev.frames;

import nycdev.controllers.PersonalLibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PersonalLibrary {
    JFrame mainFrame;
    RegisterBookFrame registerFrame;
    BookResearchFrame researchFrame;
    JMenuBar menuBar = new JMenuBar();
    JLabel title, image;
    ImageIcon reiImg = new ImageIcon("src/main/resources/assets/reiImg.png");

    PersonalLibrarySystem personalLibrarySys;

    public PersonalLibrary() {
        personalLibrarySys = new PersonalLibrarySystem();
        configMenuBar();
        configLabels();
        configFrame();
    }

    private void configMenuBar() {
        JMenu registerMenu = new JMenu("Cadastrar");
        JMenuItem registerBookItem = new JMenuItem("Cadastar novo livro");
        registerBookItem.addActionListener(e -> {
            registerFrame = new RegisterBookFrame(mainFrame, personalLibrarySys);
            registerFrame.setVisible(true);
        });
        registerMenu.add(registerBookItem);

        JMenu researchMenu = new JMenu("Pesquisar");
        JMenuItem researchBookItem = new JMenuItem("Pesquisar livro");
        researchBookItem.addActionListener(e -> {
            researchFrame = new BookResearchFrame(mainFrame, personalLibrarySys);
            researchFrame.setVisible(true);
        });
        researchMenu.add(researchBookItem);

        JMenu removeMenu = new JMenu("Excluir");
        JMenuItem removeBookItem = new JMenuItem("Excluir livro");
        removeMenu.add(removeBookItem);

        JMenu salveMenu = new JMenu("Salvar");
        JMenuItem saveDataItem = new JMenuItem("Salvar dados");
        saveDataItem.addActionListener(e -> {
            personalLibrarySys.saveBooks();
            JOptionPane.showMessageDialog(mainFrame, "Dados salvos com sucesso.");
        });
        salveMenu.add(saveDataItem);

        menuBar.add(registerMenu);
        menuBar.add(researchMenu);
        menuBar.add(removeMenu);
        menuBar.add(salveMenu);
    }

    private void configLabels() {
        title = new JLabel("Bem vindo!!", JLabel.CENTER);
        title.setFont(new Font("serif", Font.BOLD, 28));
        image = new JLabel(reiImg, JLabel.CENTER);
    }

    private void configFrame() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(mainFrame, "Tem certeza de que quer fechar?");
                if (resp == JOptionPane.YES_OPTION) {
                    personalLibrarySys.saveBooks();
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

    public void run() {
        mainFrame.setVisible(true);
    }
}
