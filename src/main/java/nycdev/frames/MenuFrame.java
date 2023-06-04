package nycdev.frames;

import nycdev.PersonalLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Nycolas Kevin
 */
public class MenuFrame {
    JFrame mainFrame;
    RegisterBookFrame registerFrame;
    SearchBookFrame researchFrame;
    RemoveBookFrame removeFrame;
    JMenuBar menuBar = new JMenuBar();
    JLabel title, image;
    ImageIcon reiImg = new ImageIcon("src/main/resources/assets/rei with books.png");
    ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_library_books_black_48dp.png");

    PersonalLibrary personalLibrary;

    public MenuFrame(PersonalLibrary personalLibrary) {
        this.personalLibrary = personalLibrary;
        configMenuBar();
        configFrame();
        configComponents();
        configLayout();
    }

    private void configMenuBar() {
        JMenu registerMenu = new JMenu("Cadastrar");
        JMenuItem registerBookItem = new JMenuItem("Cadastar novo livro");
        registerBookItem.addActionListener(e -> {
            if (registerFrame == null) {
                registerFrame = new RegisterBookFrame(mainFrame, personalLibrary);
            }
            registerFrame.setVisible(true);
        });
        registerMenu.add(registerBookItem);

        JMenu researchMenu = new JMenu("Pesquisar");
        JMenuItem researchBookItem = new JMenuItem("Pesquisar livro");
        researchBookItem.addActionListener(e -> {
            if (researchFrame == null) {
                researchFrame = new SearchBookFrame(mainFrame, personalLibrary);
            }
            researchFrame.setVisible(true);
        });
        researchMenu.add(researchBookItem);

        JMenu removeMenu = new JMenu("Excluir");
        JMenuItem removeBookItem = new JMenuItem("Excluir livro");
        removeBookItem.addActionListener(e -> {
            if (removeFrame == null) {
                removeFrame = new RemoveBookFrame(mainFrame, personalLibrary);
            }
            removeFrame.setVisible(true);
        });
        removeMenu.add(removeBookItem);
        menuBar.add(registerMenu);
        menuBar.add(researchMenu);
        menuBar.add(removeMenu);
    }

    private void configFrame() {
        mainFrame = new JFrame("PersonalLibrary");
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(mainFrame, "Tem certeza de que quer fechar?");
                if (resp == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        mainFrame.setIconImage(icon.getImage());
        mainFrame.setBackground(Color.lightGray);
        mainFrame.setLocation(300, 50);
        mainFrame.setResizable(false);
        mainFrame.setSize(800, 600);
        mainFrame.setJMenuBar(menuBar);
    }


    private void configComponents() {
        title = new JLabel("Bem vindo!!", JLabel.CENTER);
        title.setFont(new Font("Noto sans", Font.BOLD, 28));
        image = new JLabel(reiImg, JLabel.CENTER);
    }

    private void configLayout() {
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.add(title);
        mainFrame.add(image);
    }

    public void setVisible(boolean b) {
        mainFrame.setVisible(b);
    }
}
