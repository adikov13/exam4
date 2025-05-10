import java.util.Scanner;
import java.util.List;

public class CatActionHandler {
    public void performAction(String action, List<Cat> cats, Scanner scanner) {
        if (cats.isEmpty()) {
            System.out.println("Нет котов для взаимодействия!");
            return;
        }

        System.out.print("Введите номер кота (1-" + cats.size() + "): ");
        try {
            int catNumber = Integer.parseInt(scanner.nextLine());
            if (catNumber < 1 || catNumber > cats.size()) {
                System.out.println("Неверный номер кота!");
                return;
            }

            Cat cat = cats.get(catNumber - 1);
            if (!cat.isAlive()) {
                System.out.println("Этот кот больше не с нами...");
                return;
            }

            switch (action) {
                case "1": cat.feed(); break;
                case "2": cat.play(); break;
                case "3": cat.heal(); break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите число!");
        }
    }
}