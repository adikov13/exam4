import java.util.List;
import java.util.ArrayList;

public class CatFactory {
    public static List<Cat> createSampleCats() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Peach", 11, 78, 86, 50));
        cats.add(new Cat("Jasper", 12, 83, 39, 43));
        cats.add(new Cat("Poppy", 9, 38, 57, 71));
        return cats;
    }
}