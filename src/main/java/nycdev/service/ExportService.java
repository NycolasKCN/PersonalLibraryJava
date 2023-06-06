package nycdev.service;

import nycdev.models.Book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportService {
  public void exportToCsv(File currentDirectory, List<Book> books) throws IOException {
    File file = new File(currentDirectory.getAbsolutePath() + "/books.csv");
    System.err.println(file);
    if (file.createNewFile()) {
      writeFile(file, books);
    }
  }

  private void writeFile(File file, List<Book> books) throws IOException {
    FileWriter w = new FileWriter(file);
    w.write("id,title,author,pages\n");

    for(Book b : books) {
      String line = String.format("%s,%s,%s,%s\n", b.getId(),b.getName(),b.getAuthor(),b.getPages());
      w.write(line);
    }

    w.close();
  }


}
