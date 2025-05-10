import java.util.Scanner;
import java.util.List;
import java.util.Comparator;

public class CatTableDisplay {
    public void display(List<Cat> cats) {
        System.out.println("\n- +---+---------+--------+----------+------------+----------+-----------------+-----------+");
        System.out.println("| # | Имя     | Возраст | Здоровье | Настроение | Сытость  | Средний уровень | Действие  |");
        System.out.println("- +---+---------+--------+----------+------------+----------+-----------------+-----------+");

        int i = 1;
        for (Cat cat : cats) {
            String actionStatus = cat.isActionPerformedToday() ? "✓" : "";
            System.out.printf("| %d | %-7s | %-6d | %-8d | %-10d | %-8d | %-15.0f | %-9s |%n",
                    i++, cat.getName(), cat.getAge(), cat.getHealth(),
                    cat.getMood(), cat.getSatiety(), cat.getAverageLifeLevel(), actionStatus);
        }

        System.out.println("- +---+---------+--------+----------+------------+----------+-----------------+-----------+");
    }

    public void sortCats(List<Cat> cats, Scanner scanner) {
        System.out.println("\nСортировать по:");
        System.out.println("1: Имени");
        System.out.println("2: Возрасту");
        System.out.println("3: Здоровью");
        System.out.println("4: Настроению");
        System.out.println("5: Сытости");
        System.out.println("6: Среднему уровню");
        System.out.print("Выберите критерий: ");

        String choice = scanner.nextLine().trim();
        Comparator<Cat> comparator = getComparator(choice);

        if (comparator != null) {
            cats.sort(comparator);
            System.out.println("Сортировка применена!");
        } else {
            System.out.println("Неверный выбор, сортировка не изменена");
        }
    }

    private Comparator<Cat> getComparator(String choice) {
        switch (choice) {
            case "1": return Comparator.comparing(Cat::getName);
            case "2": return Comparator.comparingInt(Cat::getAge);
            case "3": return Comparator.comparingInt(Cat::getHealth).reversed();
            case "4": return Comparator.comparingInt(Cat::getMood).reversed();
            case "5": return Comparator.comparingInt(Cat::getSatiety).reversed();
            case "6": return Comparator.comparingDouble(Cat::getAverageLifeLevel).reversed();
            default: return null;
        }
    }
}