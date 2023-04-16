package nycdev.frames;

import nycdev.controllers.PersonalLibrarySystem;
import nycdev.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PesquisaLivroFrame {
    String[] collumns = {"Título", "Autor", "Número de páginas"};
    String[][] resultado;
    private final PersonalLibrarySystem sys;
    private final JFrame parent;
    JTextField query;
    JCheckBox name, author;
    JButton pesquisar;
    JFrame frame;
    JTable output;
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scrollPane;
    public PesquisaLivroFrame(JFrame parent, PersonalLibrarySystem sys) {
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
        inputText.add(name);
        inputText.add(author);

        scrollPane = new JScrollPane(output);

        BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        frame.setLayout(layout);
        frame.add(inputText, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(pesquisar, BorderLayout.AFTER_LAST_LINE);
    }

    private void configComponents() {
        query = new JTextField();
        name = new JCheckBox();
        name.setText("Pesquisar pelo Título");
        author = new JCheckBox();
        author.setText("Pequisar pelo Autor");
        pesquisar = new JButton("Pesquisar");
        pesquisar.addActionListener(e -> {
            pesquisar();
        });
        output = new JTable();
    }

     private void pesquisar() {
        String termo = query.getText();
        if (name.isSelected() && author.isSelected()) {
            JOptionPane.showMessageDialog(frame, "Marque apenas um modo de pesquisa");
            return;
        }
        if (name.isSelected()) {
            ArrayList<Book> result = (ArrayList<Book>) sys.getBooks();
            resultado = new String[result.size()][3];
            for (int i = 0; i < result.size(); i++) {
                Book b = result.get(i);
                resultado[i][0] = b.getName();
                resultado[i][1] = b.getAuthor();
                if (b.getPages() == null) {
                    resultado[i][2] = " ";
                } else {
                    resultado[i][2] = b.getPages();
                }
            }
        } else if (author.isSelected()) {
            ArrayList<Book> result = (ArrayList<Book>) sys.getBooks();
            resultado = new String[result.size()][3];
            for (int i = 0; i < result.size(); i++) {
                Book b = result.get(i);
                resultado[i][0] = b.getName();
                resultado[i][1] = b.getAuthor();
                if (b.getPages() == null) {
                    resultado[i][2] = " ";
                } else {
                    resultado[i][2] = b.getPages();
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Marque um modo de pesquisa");
            return;
        }
        model = new DefaultTableModel(resultado, collumns);
        output.setModel(model);
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
