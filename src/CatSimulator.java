// CatSimulator.java
import java.util.*;
import java.util.stream.Collectors;

public class CatSimulator {
    public static void main(String[] args) {
        List<Cat> cats = createSampleCats();
        displayCatTable(cats);
    }

    public static List<Cat> createSampleCats() {
        return Arrays.asList(
                new Cat("Peach", 11, 78, 86, 50),
                new Cat("Jasper", 12, 83, 39, 43),
                new Cat("Poppy", 9, 38, 57, 71)
        );
    }

    public static void displayCatTable(List<Cat> cats) {
        List<Cat> sortedCats = cats.stream()
                .sorted(Comparator.comparingDouble(Cat::getAverageLifeLevel).reversed())
                .collect(Collectors.toList());

        System.out.println("- +---+---------+--------+----------+------------+----------+-----------------+");
        System.out.println("| # | имя     | возраст | здоровье | настроение | сытость  | средний уровень |");
        System.out.println("- +---+---------+--------+----------+------------+----------+-----------------+");

        for (int i = 0; i < sortedCats.size(); i++) {
            Cat cat = sortedCats.get(i);
            System.out.printf("| %d | %-7s | %-6d | %-8d | %-10d | %-8d | %-15.0f |%n",
                    i + 1, cat.getName(), cat.getAge(), cat.getHealth(),
                    cat.getMood(), cat.getSatiety(), cat.getAverageLifeLevel());
        }

        System.out.println("- +---+---------+--------+----------+------------+----------+-----------------+");
    }
}