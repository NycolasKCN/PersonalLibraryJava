package nycdev.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nycdev.Util;
import nycdev.dto.BookDto;
import nycdev.models.Book;
import nycdev.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static nycdev.Util.convertJsonToString;

public class WebService {
  private final String webService = "http://localhost:8080/";

  public Book registerBook(User user, Book book) throws AuthenticationException, BookAlreadyExistException {
    try {
      URL url = new URL(webService + "v1/book");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Authorization", "Bearer " + user.getToken());
      connection.setRequestProperty("Content-Type", "application/json");

      String data = String.format("{\"userId\" : %d,\"name\" : \"%s\",\"author\": \"%s\",\"pages\": \"%s\"}",
              user.getId(), book.getName(), book.getAuthor(), book.getPages());

      byte[] out = data.getBytes(StandardCharsets.UTF_8);

      OutputStream stream = connection.getOutputStream();
      stream.write(out);

      if (connection.getResponseCode() == 401) {
        throw new AuthenticationException("User with id: " + user.getId() + " don't have access to register book");
      }
      if (connection.getResponseCode() == 400) {
        throw new BookAlreadyExistException("Book with name: " + book.getName() + " already register");
      }

      BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      Gson gson = new Gson();
      BookDto bookDto = gson.fromJson(response, BookDto.class);
      System.out.println(bookDto);
      connection.disconnect();
      book.setId(bookDto.getId());
      return book;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public void deleteBook(User user, Book book) throws AuthenticationException, BookNotFoundException {
    try {
      String urlString = String.format("%s%s%d",webService,"v1/book/delete/",book.getId());
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("DELETE");
      connection.setRequestProperty("Authorization", "Bearer " + user.getToken());
      connection.setRequestProperty("Content-Type", "application/json");

      if (connection.getResponseCode() == 401) {
        throw new AuthenticationException("User don't have permission do delete book with id: " + book.getId());
      }
      if (connection.getResponseCode() == 400) {
        throw new BookNotFoundException("Book with id: " + book.getId() + " not founded.");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Book> allBooksUser(User user) {
    try {
      URL url = new URL(webService + "v1/book/all");
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Authorization", "Bearer " + user.getToken());
      connection.setRequestProperty("Content-Type", "application/json");

      String data = String.format("{\"userId\" : %d}", user.getId());

      byte[] out = data.getBytes(StandardCharsets.UTF_8);

      OutputStream stream = connection.getOutputStream();
      stream.write(out);

      BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      List<Book> listBook = getBookDto(response);
      connection.disconnect();
      return listBook;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Book> findBooksByName(User user, String name) {
    try {
      String urlString = webService + "v1/book/findByName/" + name;
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Authorization", "Bearer " + user.getToken());
      connection.setRequestProperty("Content-Type", "application/json");

      BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      List<Book> listBook = getBookDto(response);
      connection.disconnect();
      return listBook;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Book> findBooksByAuthor(User user, String author) {
    try {
      String urlString = webService + "v1/book/findByAuthor/" + author;
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Authorization", "Bearer " + user.getToken());
      connection.setRequestProperty("Content-Type", "application/json");

      BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      List<Book> listBook = getBookDto(response);
      connection.disconnect();
      return listBook;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private static List<Book> getBookDto(BufferedReader response) throws IOException {
    String json = Util.convertJsonToString(response);
    ObjectMapper objectMapper = new ObjectMapper();
    List<BookDto> listBook = objectMapper.readValue(json, new TypeReference<List<BookDto>>(){});
    return listBook.stream().map(Book::new).toList();
  }

  public void registerUser(String name, String login, String password) throws UserAlreadyExistException {
    String urlString = webService + "v1/user";

    try {
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/json");

      String data = String.format("{\"name\": \"%s\",\"login\":\"%s\",\"password\": \"%s\"}", name, login, password);

      byte[] out = data.getBytes(StandardCharsets.UTF_8);

      OutputStream stream = connection.getOutputStream();
      stream.write(out);

      connection.disconnect();

      if (connection.getResponseCode() == 400) {
        throw new UserAlreadyExistException("User with login: " + login + " already exist.");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public User authenticateUser(String login, String password) throws AuthenticationException {
    try {
      URL url = new URL(webService + "token");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/json");

      String data = String.format("{\"login\" : \"%s\",\"password\" : \"%s\"}", login, password);

      byte[] out = data.getBytes(StandardCharsets.UTF_8);

      OutputStream stream = connection.getOutputStream();
      stream.write(out);

      if (connection.getResponseCode() == 400) {
        throw new AuthenticationException("Login or password is incorrect.");
      }

      BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      Gson gson = new Gson();
      User user = gson.fromJson(convertJsonToString(response), User.class);
      connection.disconnect();
      return user;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
