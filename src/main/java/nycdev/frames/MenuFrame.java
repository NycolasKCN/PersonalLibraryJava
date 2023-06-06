package nycdev.frames;

import nycdev.PersonalLibrary;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Nycolas Kevin
 */
public class MenuFrame {
  JFrame frame;
  JPanel contentPanel;
  RegisterBookFrame registerFrame;
  SearchBookFrame researchFrame;
  RemoveBookFrame removeFrame;
  JMenuBar menuBar = new JMenuBar();
  JLabel title, image;
  JButton addButton, searchButton, deleteButton;
  ImageIcon titleImg = new ImageIcon("src/main/resources/assets/book_icon.png");
  ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_library_books_black_48dp.png");
  ImageIcon iconAdd = new ImageIcon("src/main/resources/assets/2x/outline_add_circle_outline_black_48dp.png");
  ImageIcon iconDelete = new ImageIcon("src/main/resources/assets/2x/outline_delete_black_48dp.png");
  ImageIcon iconSearch = new ImageIcon("src/main/resources/assets/2x/outline_search_black_48dp.png");
  PersonalLibrary personalLibrary;

  public MenuFrame(PersonalLibrary personalLibrary) {
    this.personalLibrary = personalLibrary;
    configMenuBar();
    configFrame();
    configComponents();
    configLayout();
    Timer timer = new Timer(30, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        contentPanel.repaint();
      }
    });
    timer.start();

  }

  private void configMenuBar() {
    JMenu fileMenu = new JMenu("File");

    JMenuItem registerBookItem = new JMenuItem("New book");
    registerBookItem.addActionListener((e) -> {
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
    darkMode.addActionListener((e) -> {
      JOptionPane.showMessageDialog(frame, "Coming soon.");
    });
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
    image = new JLabel(titleImg, JLabel.CENTER);

    addButton = new JButton(iconAdd);
    addButton.setText("Add new book");
    addButton.setFont(new Font("Noto sans", Font.BOLD, 16));
    addButton.setFocusPainted(false);
    addButton.setBackground(new Color(204, 230, 255));
    addButton.setBorder(BorderFactory.createLineBorder(new Color(102, 181, 255), 2, true));

    searchButton = new JButton(iconSearch);
    searchButton.setText("Search books");
    searchButton.setFont(new Font("Noto sans", Font.BOLD, 16));
    searchButton.setFocusPainted(false);
    searchButton.setBackground(new Color(204, 230, 255));
    searchButton.setBorder(BorderFactory.createLineBorder(new Color(102, 181, 255), 2, true));

    deleteButton = new JButton(iconDelete);
    deleteButton.setText("Delete book");
    deleteButton.setFont(new Font("Noto sans", Font.BOLD, 16));
    deleteButton.setFocusPainted(false);
    deleteButton.setBackground(new Color(204, 230, 255));
    deleteButton.setBorder(BorderFactory.createLineBorder(new Color(102, 181, 255), 2, true));

    addButton.addActionListener((e) -> {
      if (registerFrame == null) {
        registerFrame = new RegisterBookFrame(frame, personalLibrary);
      }
      registerFrame.setVisible(true);
    });

    searchButton.addActionListener((e) -> {
      if (researchFrame == null) {
        researchFrame = new SearchBookFrame(frame, personalLibrary);
      }
      researchFrame.setVisible(true);
    });

    deleteButton.addActionListener((e) -> {
      if (removeFrame == null) {
        removeFrame = new RemoveBookFrame(frame, personalLibrary);
      }
      removeFrame.setVisible(true);
    });

  }

    private void configLayout() {
      contentPanel = new ContentPanel();

      JPanel topPanel = new JPanel();
      topPanel.setBackground(new Color(0,0,0,0));
      topPanel.setLayout(new FlowLayout());
      topPanel.add(image);
      topPanel.add(title);

      JPanel bottomPanel = new JPanel();
      bottomPanel.setLayout(new GridLayout(1,3,20, 20));
      bottomPanel.setBorder(BorderFactory.createEmptyBorder(50  ,20,50,20));
      bottomPanel.setBackground(new Color(0,0,0,0));

      bottomPanel.add(addButton);
      bottomPanel.add(searchButton);
      bottomPanel.add(deleteButton);

      contentPanel.setLayout(new GridLayout(2, 1));
      contentPanel.add(topPanel);
      contentPanel.add(bottomPanel);

      frame.setContentPane(contentPanel);
    }

  public void setVisible(boolean b) {
    frame.setVisible(b);
  }

  private static class RoundedBorder implements Border {

    private int radius;


    RoundedBorder(int radius) {
      this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
      return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
      return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
  }
}
