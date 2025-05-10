import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cat> cats = CatFactory.createSampleCats();
        interactWithCats(cats);
        CatTableDisplay.display(cats);
    }

    private static void interactWithCats(List<Cat> cats) {
        cats.get(0).feed(20);
        cats.get(1).play(15);
        cats.get(2).heal(10);
    }
}