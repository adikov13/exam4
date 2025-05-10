import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class CatSimulator {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Cat> cats = new ArrayList<>();
    private int currentDay = 1;
    private CatTableDisplay tableDisplay = new CatTableDisplay();
    private CatActionHandler actionHandler = new CatActionHandler();

    public void run() {
        initializeSampleCats();

        while (true) {
            System.out.println("\n=== День " + currentDay + " ===");
            tableDisplay.display(cats);

            String choice = showMenuAndGetChoice();

            if (choice.equals("q")) {
                System.out.println("До свидания!");
                return;
            }

            handleUserChoice(choice);
            cats.removeIf(cat -> !cat.isAlive());
        }
    }

    private String showMenuAndGetChoice() {
        System.out.println("\n1: Покормить кота");
        System.out.println("2: Поиграть с котом");
        System.out.println("3: К ветеринару");
        System.out.println("a: Завести нового питомца");
        System.out.println("n: Следующий день");
        System.out.println("s: Сортировать котов");
        System.out.println("q: Выйти");
        System.out.print("Выберите действие: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private void handleUserChoice(String choice) {
        switch (choice) {
            case "1":
            case "2":
            case "3":
                actionHandler.performAction(choice, cats, scanner);
                break;
            case "a":
                addNewCat();
                break;
            case "n":
                nextDay();
                break;
            case "s":
                tableDisplay.sortCats(cats, scanner);
                break;
            default:
                System.out.println("Неверный ввод, попробуйте еще раз");
        }
    }

    private void initializeSampleCats() {
        cats.add(new Cat("Peach", 11, 78, 86, 50));
        cats.add(new Cat("Seth", 9, 94, 53, 30));
        cats.add(new Cat("Jasper", 12, 83, 39, 43));
        cats.add(new Cat("Poppy", 9, 38, 57, 71));
    }

    private void addNewCat() {
        Cat newCat = CatFactory.createCat(scanner);
        if (newCat != null) {
            cats.add(newCat);
            System.out.println("Кот " + newCat.getName() + " добавлен!");
        }
    }

    private void nextDay() {
        currentDay++;
        System.out.println("\n=== Наступил день " + currentDay + " ===");
        cats.forEach(Cat::nextDay);
    }
}