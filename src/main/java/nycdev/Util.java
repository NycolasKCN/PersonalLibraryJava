package nycdev;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {
    public static String convertJsonToString(BufferedReader bufferedReader) throws IOException {
        StringBuilder jsonEmString = new StringBuilder();
        for (String response = bufferedReader.readLine(); response != null; response = bufferedReader.readLine()){
            jsonEmString.append(response);

        }
        return jsonEmString.toString();
    }
}
