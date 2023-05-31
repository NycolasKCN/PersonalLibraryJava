package nycdev.frames;

import nycdev.PersonalLibrarySystem;
import nycdev.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static nycdev.frames.SearchBookFrame.configTableModel;
import static nycdev.frames.SearchBookFrame.populateVector;

/**
 * @author Nycolas Kevin
 */
public class RemoveBookFrame {
    ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_delete_black_48dp.png");
    PersonalLibrarySystem sys;
    JFrame frame, parent;
    JLabel titleLabel, authorLabel;
    JTextField titleTF, authorTF;
    JButton removeBt;
    JTable table;
    DefaultTableModel tableModel;
    String[] columns = {"id", "Título", "Autor", "Número de páginas"};

    public RemoveBookFrame(JFrame parent, PersonalLibrarySystem sys) {
        this.parent = parent;
        this.sys = sys;
        configFrame();
        configComponents();
        updateTable(sys.getAllBooks());
        configLayout();
    }

    public void configFrame() {
        frame = new JFrame("Apagar livro");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocation(getPosition());
        frame.setBackground(Color.lightGray);
    }

    public void configComponents() {
        titleLabel = new JLabel("Título: ", JLabel.CENTER);
        titleLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        authorLabel = new JLabel("Autor: ", JLabel.CENTER);
        authorLabel.setFont(new Font("Noto Sans", Font.PLAIN, 14));

        titleTF = new JTextField();
        authorTF = new JTextField();

        removeBt = new JButton("Apagar");
        removeBt.addActionListener(e -> {
            String title = titleTF.getText();
            String author = authorTF.getText();
            boolean success = sys.removeBook(title, author);
            if (success) {
                JOptionPane.showMessageDialog(frame, "Livro apagado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(frame, "Livro não existe ou não encontrado.");
            }
            frame.dispose();
        });

        table = new JTable();
    }

    public void configLayout() {
        GridLayout panelLayout = new GridLayout(2, 2);
        panelLayout.setVgap(6);
        JPanel inputPanel = new JPanel(panelLayout);
        inputPanel.setBounds(0, 5, 600, 200);
        inputPanel.add(titleLabel);
        inputPanel.add(titleTF);
        inputPanel.add(authorLabel);
        inputPanel.add(authorTF);

        JScrollPane tablePanel = new JScrollPane(table);

        BorderLayout frameLayout = new BorderLayout();
        frameLayout.setVgap(10);
        frameLayout.setHgap(30);
        frame.setLayout(frameLayout);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        frame.add(removeBt, BorderLayout.AFTER_LAST_LINE);
    }

    private void updateTable(List<Book> b) {
        String[][] data = populateVector(b);
        tableModel = new DefaultTableModel(data, columns);
        configTableModel(table, tableModel);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
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
        int x = (int) ((posParent.getX() + (sizeParent.getWidth() / 2)) - (sizeThis.getWidth() / 2));
        int y = (int) ((posParent.getY() + (sizeParent.getHeight() / 2)) - (sizeThis.getHeight() / 2));
        return new Point(x, y);
    }

}
