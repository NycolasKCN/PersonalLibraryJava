package nycdev.service;

import com.google.gson.Gson;
import nycdev.AuthenticationException;
import nycdev.UserAlreadyExistException;
import nycdev.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginService {
    private String webService = "http://localhost:8080/v1/";

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
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            String data = String.format("{\"login\" : \"%s\",\"password\" : \"%s\"}", login, password);

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            if (http.getResponseCode() == 400) {
                throw new AuthenticationException("Login or password is incorrect.");
            }

            BufferedReader response = new BufferedReader(new InputStreamReader(http.getInputStream()));
            Gson gson = new Gson();
            return gson.fromJson(LoginService.convertJsonToString(response), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertJsonToString(BufferedReader bufferedReader) throws IOException {
        StringBuilder jsonEmString = new StringBuilder();
        for (String response = bufferedReader.readLine(); response != null; response = bufferedReader.readLine()){
            jsonEmString.append(response);

        }
        return jsonEmString.toString();
    }
}
