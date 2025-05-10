import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class CatJsonRepository {
    private static final Gson gson = new Gson();
    private static final String FILE_NAME = "cats.json";

    public static void saveCats(List<Cat> cats) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(cats, writer);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении файла: " + e.getMessage());
        }
    }

    public static List<Cat> loadCats() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type listType = new TypeToken<List<Cat>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке файла: " + e.getMessage());
            return null;
        }
    }
}