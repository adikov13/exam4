import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Cat> cats = new ArrayList<>();

    public static void main(String[] args) {
        initializeSampleCats();
        showMainMenu();
    }

    private static void initializeSampleCats() {
        cats.add(new Cat("Peach", 11, 78, 86, 50));
        cats.add(new Cat("Seth", 9, 94, 53, 30));
        cats.add(new Cat("Jasper", 12, 83, 39, 43));
        cats.add(new Cat("Poppy", 9, 38, 57, 71));
    }

    private static void showMainMenu() {
        while (true) {
            CatTableDisplay.display(cats);
            System.out.println("\n1: Покормить кота");
            System.out.println("2: Поиграть с котом");
            System.out.println("3: К ветеринару");
            System.out.println("a: Завести нового питомца");
            System.out.println("q: Выйти");
            System.out.print("Выберите действие: ");

            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1":
                case "2":
                case "3":
                    performAction(choice);
                    break;
                case "a":
                    addNewCat();
                    break;
                case "q":
                    System.out.println("До свидания!");
                    return;
                default:
                    System.out.println("Неверный ввод, попробуйте еще раз");
            }
        }
    }

    private static void performAction(String action) {
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
            switch (action) {
                case "1": cat.feed(); break;
                case "2": cat.play(); break;
                case "3": cat.heal(); break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите число!");
        }
    }

    private static void addNewCat() {
        System.out.println("\nДобавление нового кота");

        String name;
        while (true) {
            System.out.print("Введите имя кота: ");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Имя не может быть пустым!");
        }

        int age = 0;
        while (age == 0) {
            System.out.print("Введите возраст кота (1-18): ");
            try {
                age = Integer.parseInt(scanner.nextLine());
                if (age < 1 || age > 18) {
                    System.out.println("Возраст должен быть от 1 до 18!");
                    age = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число!");
            }
        }

        cats.add(new Cat(name, age));
        System.out.println("Кот " + name + " добавлен!");
    }
}