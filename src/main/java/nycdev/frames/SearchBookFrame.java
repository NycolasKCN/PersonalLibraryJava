package nycdev.frames;

import nycdev.controllers.PersonalLibrarySystem;
import nycdev.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author Nycolas Kevin
 */
public class SearchBookFrame {
    private final PersonalLibrarySystem sys;
    private final JFrame parent;
    String[] collumns = {"id", "Título", "Autor", "Número de páginas"};
    String[][] resultado;
    JTextField query;
    JCheckBox nameCB, authorCB;
    JButton pesquisarBt;
    JFrame frame;
    JTable tableResult;
    DefaultTableModel tableModel = new DefaultTableModel();
    JScrollPane scrollPane;

    ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_search_black_48dp.png");

    public SearchBookFrame(JFrame parent, PersonalLibrarySystem sys) {
        this.parent = parent;
        this.sys = sys;

        configFrame();
        configComponents();
        configLayout();
    }

    public static void configTableModel(JTable table, DefaultTableModel model) {
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(254);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setResizable(false);
        table.getColumnModel().getColumn(3).setPreferredWidth(130);
        table.getColumnModel().getColumn(3).setResizable(false);
    }

    public static String[][] populateVector(List<Book> list) {
        String[][] r = new String[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            Book b = list.get(i);
            r[i][0] = "" + (i + 1);
            r[i][1] = b.getName();
            r[i][2] = b.getAuthor();
            if (b.getPages() == null) {
                r[i][3] = " ";
            } else {
                r[i][3] = b.getPages();
            }
        }
        return r;
    }

    private void configFrame() {
        frame = new JFrame("Pesquisar livros");
        frame.setIconImage(icon.getImage());
        frame.setBackground(Color.lightGray);
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setLocation(getPosition());
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

    private void configLayout() {
        JPanel inputText = new JPanel();
        inputText.setLayout(new GridLayout(3, 1));
        inputText.setBounds(0, 0, 600, 150);
        inputText.add(query);
        inputText.add(nameCB);
        inputText.add(authorCB);

        scrollPane = new JScrollPane(tableResult);

        BorderLayout layout = new BorderLayout();
        layout.setVgap(10);
        frame.setLayout(layout);
        frame.add(inputText, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(pesquisarBt, BorderLayout.AFTER_LAST_LINE);
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
        SearchBookFrame.configTableModel(tableResult, tableModel);
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

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
