package nycdev.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import nycdev.Util;
import nycdev.dto.BookDto;
import nycdev.models.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BookService {
    private final String webService = "http://localhost:8080/v1/";

    public Book registerBook(String token, Long userId, Book book) throws AuthenticationException, BookAlreadyExistException {
        try {
            URL url = new URL(webService + "book");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");

            String data = String.format("{\"userId\" : %d,\"name\" : \"%s\",\"author\": \"%s\"}", userId, book.getName(), book.getAuthor());

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = connection.getOutputStream();
            stream.write(out);

            if (connection.getResponseCode() == 401) {
                throw new AuthenticationException("User with id: " + userId + " don't have access to register book");
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

    public void deleteBook(String token, Long bookId) throws AuthenticationException, BookNotFoundException {
        try {
            String urlString = String.format("%s%s%d",webService,"book/delete/",bookId);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");

            if (connection.getResponseCode() == 401) {
                throw new AuthenticationException("User don't have permission do delete book with id: " + bookId);
            }
            if (connection.getResponseCode() == 400) {
                throw new BookNotFoundException("Book with id: " + bookId + " not founded.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> allBooksUser(String token, Long userId) {
        try {
            URL url = new URL(webService + "book/all");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");

            String data = String.format("{\"userId\" : %d}", userId);

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = connection.getOutputStream();
            stream.write(out);

            BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            List<Book> listBook = getBookDtos(response);
            connection.disconnect();
            return listBook;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Book> getBookDtos(BufferedReader response) throws IOException {
        String json = Util.convertJsonToString(response);
        ObjectMapper objectMapper = new ObjectMapper();
        List<BookDto> listBook = objectMapper.readValue(json, new TypeReference<List<BookDto>>(){});
        return listBook.stream().map(Book::new).toList();
    }
}
