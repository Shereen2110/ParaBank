package Utitites;

import com.google.gson.Gson;
import java.io.FileReader;

public class JsonReader {

    public static <T> T readJson(String filePath, Class<T> clazz) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(
                    new FileReader(filePath),
                    clazz
            );
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON file: " + filePath);
        }
    }
}
