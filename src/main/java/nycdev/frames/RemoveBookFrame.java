package nycdev.frames;

import nycdev.PersonalLibrary;
import nycdev.models.Book;
import nycdev.service.AuthenticationException;
import nycdev.service.BookNotFoundException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static nycdev.Util.configTableModel;
import static nycdev.Util.getPosition;
import static nycdev.Util.populateVector;

/**
 * @author Nycolas Kevin
 */
public class RemoveBookFrame {
    ImageIcon icon = new ImageIcon("src/main/resources/assets/2x/outline_delete_black_48dp.png");
    PersonalLibrary personalLibrary;
    JFrame frame, parent;
    JButton removeBt;
    JTable table;
    DefaultTableModel tableModel;
    String[] columns = {"id", "Título", "Autor", "Número de páginas"};
    List<Book> books;


    public RemoveBookFrame(JFrame parent, PersonalLibrary personalLibrary) {
        this.parent = parent;
        this.personalLibrary = personalLibrary;
        setBooks();
        configFrame();
        configComponents();
        updateTable(books);
        configLayout();
    }

    private void setBooks() {
        books = personalLibrary.getWebService().allBooksUser(personalLibrary.getLogedUser());
    }

    public void configFrame() {
        frame = new JFrame("Apagar livro");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLocation(getPosition(parent, frame));
        frame.setBackground(Color.lightGray);
    }

    public void configComponents() {
        removeBt = new JButton("Apagar");
        removeBt.addActionListener((e) -> {
            Book book = books.get(table.getSelectedRow());
            try {
                personalLibrary.getWebService().deleteBook(personalLibrary.getLogedUser(), book);
                books = personalLibrary.getWebService().allBooksUser(personalLibrary.getLogedUser());
            } catch (AuthenticationException ex) {
                JOptionPane.showMessageDialog(frame, "You don't have permission to delete this book.");
            } catch (BookNotFoundException ex) {
                JOptionPane.showMessageDialog(frame, "Book not founded.");
            }
            JOptionPane.showMessageDialog(frame,"Livro removido com sucesso.");

            updateTable(books);
        });
        table = new JTable();
    }

    public void configLayout() {
        JScrollPane tablePanel = new JScrollPane(table);

        BorderLayout frameLayout = new BorderLayout();
        frameLayout.setVgap(10);
        frameLayout.setHgap(30);
        frame.setLayout(frameLayout);
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
}
