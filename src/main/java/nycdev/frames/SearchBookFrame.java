package nycdev.frames;

import nycdev.PersonalLibrary;
import nycdev.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * @author Nycolas Kevin
 */
public class SearchBookFrame {
    private final PersonalLibrary personalLibrary;
    private final JFrame parent;
    String[] collumns = {"id", "Título", "Autor", "Número de páginas"};
    String[][] result;
    JTextField query;
    JRadioButton searchByName, getAll, searchByAuthor;
    ButtonGroup group;
    JButton pesquisarBt;
    JFrame frame;
    JTable tableResult;
    DefaultTableModel tableModel = new DefaultTableModel();
    JScrollPane scrollPane;

    ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_search_black_48dp.png");

    public SearchBookFrame(JFrame parent, PersonalLibrary personalLibrary) {
        this.parent = parent;
        this.personalLibrary = personalLibrary;

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

        searchByName = new JRadioButton();
        searchByName.setText("Pesquisar pelo Título");
        searchByAuthor = new JRadioButton();
        searchByAuthor.setText("Pequisar pelo Autor");
        getAll = new JRadioButton();
        getAll.setText("Find all");
        group = new ButtonGroup();
        group.add(getAll);
        group.add(searchByName);
        group.add(searchByAuthor);


        pesquisarBt = new JButton("Pesquisar");
        pesquisarBt.addActionListener(e -> {
            pesquisar();
        });
        tableResult = new JTable();
    }

    private void configLayout() {
        JPanel inputText = new JPanel();
        inputText.setLayout(new GridLayout(2, 1));
        inputText.setBounds(0, 0, 600, 150);
        JPanel radioButtonGroup = new JPanel();
        radioButtonGroup.add(getAll);
        radioButtonGroup.add(searchByName);
        radioButtonGroup.add(searchByAuthor);

        inputText.add(query);
        inputText.add(radioButtonGroup);

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

        if (queryText.equals("") && !getAll.isSelected()) {
            JOptionPane.showMessageDialog(frame, "Digite um algo na caixa de seleção");
            return;
        }

        if (getAll.isSelected()) {
            List<Book> listBook = personalLibrary.getWebService().allBooksUser(personalLibrary.getLogedUser());
            result = populateVector(listBook);
        } else if (searchByName.isSelected()) {
            List<Book> listBook = personalLibrary.getWebService().findBooksByName(personalLibrary.getLogedUser(), queryText);
            result = populateVector(listBook);
        } else if (searchByAuthor.isSelected()) {
            List<Book> listBook = personalLibrary.getWebService().findBooksByAuthor(personalLibrary.getLogedUser(), queryText);
            result = populateVector(listBook);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um modo de pesquisa");
            return;
        }
        tableModel = new DefaultTableModel(result, collumns);
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
