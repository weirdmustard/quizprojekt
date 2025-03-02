package quiz;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class QuizLoader {
    public static List<Question> loadQuestions() {
        try {
            String json = new String(Files.readAllBytes(Paths.get("questions.json")), StandardCharsets.UTF_8);
            return new Gson().fromJson(json, new TypeToken<ArrayList<Question>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}