import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CatTableDisplay {
    public static void display(List<Cat> cats) {
        List<Cat> sortedCats = cats.stream()
                .sorted(Comparator.comparingDouble(Cat::getAverageLifeLevel).reversed())
                .collect(Collectors.toList());

        System.out.println("\n- +---+---------+--------+----------+------------+----------+-----------------+");
        System.out.println("| # | Имя     | Возраст | Здоровье | Настроение | Сытость  | Средний уровень |");
        System.out.println("- +---+---------+--------+----------+------------+----------+-----------------+");

        int i = 1;
        for (Cat cat : sortedCats) {
            System.out.printf("| %d | %-7s | %-6d | %-8d | %-10d | %-8d | %-15.0f |%n",
                    i++, cat.getName(), cat.getAge(), cat.getHealth(),
                    cat.getMood(), cat.getSatiety(), cat.getAverageLifeLevel());
        }

        System.out.println("- +---+---------+--------+----------+------------+----------+-----------------+");
    }
}