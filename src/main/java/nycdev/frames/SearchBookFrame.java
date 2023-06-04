package nycdev.frames;

import nycdev.PersonalLibrary;
import nycdev.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static nycdev.Util.*;

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


    private void configFrame() {
        frame = new JFrame("Pesquisar livros");
        frame.setIconImage(icon.getImage());
        frame.setBackground(Color.lightGray);
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.setLocation(getPosition(parent, frame));
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
        configTableModel(tableResult, tableModel);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
