package nycdev.service;

import com.google.gson.Gson;
import nycdev.AuthenticationException;
import nycdev.UserAlreadyExistException;
import nycdev.models.User;
import static nycdev.Util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginService {
    private final String webService = "http://localhost:8080/v1/";

    public void registerUser(String name, String login, String password) throws UserAlreadyExistException {
        String urlString = webService + "user";

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
            URL url = new URL("http://localhost:8080/token");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
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

    public static void main(String[] args) {
        LoginService service = new LoginService();
        try {
            service.registerUser("Nycolas Kevin", "nycolas.costa", "12345678");
            User user = service.authenticateUser("nycolas.costa", "12345678");
            System.out.println(user);
        } catch (UserAlreadyExistException | AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
