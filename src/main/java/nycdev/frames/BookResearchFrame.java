package nycdev.frames;

import nycdev.controllers.PersonalLibrarySystem;
import nycdev.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookResearchFrame {
    private final PersonalLibrarySystem sys;
    private final JFrame parent;
    String[] collumns = {"Título", "Autor", "Número de páginas"};
    String[][] resultado;
    JTextField query;
    JCheckBox nameCB, authorCB;
    JButton pesquisarBt;
    JFrame frame;
    JTable tableResult;
    DefaultTableModel tableModel = new DefaultTableModel();
    JScrollPane scrollPane;

    public BookResearchFrame(JFrame parent, PersonalLibrarySystem sys) {
        this.parent = parent;
        this.sys = sys;

        configFrame();
        configComponents();
        configLayout();
    }

    private void configFrame() {
        frame = new JFrame();
        frame.setBackground(Color.lightGray);
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setTitle("Pesquisar");
        Point pos = getMidOfParent();
        frame.setLocation(pos);
    }

    private void configLayout() {
        JPanel inputText = new JPanel();
        inputText.setLayout(new GridLayout(3, 1));
        inputText.setBounds(0, 0, 600, 150);
        inputText.add(query);
        inputText.add(nameCB);
        inputText.add(authorCB);

        scrollPane = new JScrollPane(tableResult);

        BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        frame.setLayout(layout);
        frame.add(inputText, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(pesquisarBt, BorderLayout.AFTER_LAST_LINE);
    }

    private void configComponents() {
        query = new JTextField();
        nameCB = new JCheckBox();
        nameCB.setText("Pesquisar pelo Título");
        authorCB = new JCheckBox();
        authorCB.setText("Pequisar pelo Autor");
        pesquisarBt = new JButton("Pesquisar");
        pesquisarBt.addActionListener(e -> {
            pesquisar();
        });
        tableResult = new JTable();
    }

    private void pesquisar() {
        String queryText = query.getText();
        if (nameCB.isSelected() && authorCB.isSelected()) {
            JOptionPane.showMessageDialog(frame, "Selecione apenas um modo de pesquisa");
            return;
        }
        if (nameCB.isSelected()) {
            List<Book> result = sys.findBooksByTitle(queryText);
            resultado = populateVector(result);
        } else if (authorCB.isSelected()) {
            List<Book> result = sys.findBooksByAuthor(queryText);
            resultado = populateVector(result);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecion um modo de pesquisa");
            return;
        }
        tableModel = new DefaultTableModel(resultado, collumns);
        tableResult.setModel(tableModel);
    }

    private String[][] populateVector(List<Book> list) {
        String[][] r = new String[list.size()][3];
        for (int i = 0; i < list.size(); i++) {
            Book b = list.get(i);
            r[i][0] = b.getName();
            r[i][1] = b.getAuthor();
            if (b.getPages() == null) {
                r[i][2] = " ";
            } else {
                r[i][2] = b.getPages();
            }
        }
        return r;
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
        int x = (int) ((posParent.getX() + (sizeParent.getWidth() / 2)) - (sizeThis.getWidth() / 2));
        int y = (int) ((posParent.getY() + (sizeParent.getHeight() / 2)) - (sizeThis.getHeight() / 2));
        return new Point(x, y);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
