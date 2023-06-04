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
  JFrame frame;
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
    JMenu fileMenu = new JMenu("File");

    JMenuItem registerBookItem = new JMenuItem("New book");
    registerBookItem.addActionListener(e -> {
      if (registerFrame == null) {
        registerFrame = new RegisterBookFrame(frame, personalLibrary);
      }
      registerFrame.setVisible(true);
    });

    JMenuItem removeBookItem = new JMenuItem("Delete book");
    removeBookItem.addActionListener((e) -> {
      if (removeFrame == null) {
        removeFrame = new RemoveBookFrame(frame, personalLibrary);
      }
      removeFrame.setVisible(true);
    });

    JMenuItem searchBookItem = new JMenuItem("Search book");
    searchBookItem.addActionListener((e) -> {
      if (researchFrame == null) {
        researchFrame = new SearchBookFrame(frame, personalLibrary);
      }
      researchFrame.setVisible(true);
    });

    JSeparator separator = new JSeparator();

    JMenu exportItem = new JMenu("Export");

    JMenuItem toCsv = new JMenuItem("Books to csv");
    toCsv.addActionListener((e) -> {
      // TODO: 04/06/2023
    });
    exportItem.add(toCsv);

    JMenuItem changeAccountItem = new JMenuItem("Change account");
    changeAccountItem.addActionListener((e) -> {
      personalLibrary.changeUser();
    });


    JMenuItem exitItem = new JMenuItem("Exit");
    exitItem.addActionListener((e) -> {
      int resp = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close?");
      if (resp == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    });

    fileMenu.add(registerBookItem);
    fileMenu.add(searchBookItem);
    fileMenu.add(removeBookItem);
    fileMenu.addSeparator();
    fileMenu.add(exportItem);
    fileMenu.addSeparator();
    fileMenu.add(changeAccountItem);
    fileMenu.add(exitItem);

    JMenu windowMenu = new JMenu("Window");

    JMenu appearanceMenu = new JMenu("Appearance");
    ButtonGroup group = new ButtonGroup();
    JMenuItem darkMode = new JRadioButtonMenuItem("Dark mode");
    JMenuItem whiteMode = new JRadioButtonMenuItem("White mode");
    whiteMode.setSelected(true);
    group.add(whiteMode);
    group.add(darkMode);
    appearanceMenu.add(whiteMode);
    appearanceMenu.add(darkMode);
    windowMenu.add(appearanceMenu);

    JMenu helpMenu = new JMenu("Help");

    JMenuItem helpItem = new JMenuItem("Help");
    JMenuItem aboutItem = new JMenuItem("About");

    helpMenu.add(helpItem);
    helpMenu.add(aboutItem);

    menuBar.add(fileMenu);
    menuBar.add(windowMenu);
    menuBar.add(helpMenu);
  }

  private void configFrame() {
    frame = new JFrame("PersonalLibrary");
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int resp = JOptionPane.showConfirmDialog(frame, "Are you sure?");
        if (resp == JOptionPane.YES_OPTION) {
          System.exit(0);
        }
      }
    });
    frame.setIconImage(icon.getImage());
    frame.setBackground(Color.lightGray);
    frame.setLocation(300, 50);
    frame.setResizable(false);
    frame.setSize(800, 600);
    frame.setJMenuBar(menuBar);
  }


  private void configComponents() {
    title = new JLabel("Welcome!!", JLabel.CENTER);
    title.setFont(new Font("Noto sans", Font.BOLD, 28));
    image = new JLabel(reiImg, JLabel.CENTER);
  }

    private void configLayout() {
      frame.setLayout(new GridLayout(2, 1));
      frame.add(title);
      frame.add(image);
    }

  public void setVisible(boolean b) {
    frame.setVisible(b);
  }
}
