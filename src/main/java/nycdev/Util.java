package nycdev;

import nycdev.models.Book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Util {
  public static String convertJsonToString(BufferedReader bufferedReader) throws IOException {
    StringBuilder jsonEmString = new StringBuilder();
    for (String response = bufferedReader.readLine(); response != null; response = bufferedReader.readLine()){
      jsonEmString.append(response);

    }
    return jsonEmString.toString();
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

  private static Point getParentLocation(JFrame parent) {
    if (parent == null) {
      return new Point(0, 0);
    }
    return parent.getLocationOnScreen();
  }

  public static Point getPosition(JFrame parent, JFrame child) {
    Point posParent = getParentLocation(parent);
    Dimension sizeParent = parent.getSize();
    Dimension sizeThis = child.getSize();
    int x = (int) ((posParent.getX() + (sizeParent.getWidth() / 2)) - (sizeThis.getWidth() / 2));
    int y = (int) ((posParent.getY() + (sizeParent.getHeight() / 2)) - (sizeThis.getHeight() / 2));
    return new Point(x, y);
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
}
