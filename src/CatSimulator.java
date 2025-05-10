import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class CatSimulator {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Cat> cats = new ArrayList<>();
    private int currentDay = 1;
    private final Gson gson = new Gson();
    private static final String SAVE_FILE = "cats_save.json";

    public void run() {
        loadCats();
        Runtime.getRuntime().addShutdownHook(new Thread(this::saveCats));

        if (cats.isEmpty()) {
            initializeSampleCats();
        }

        while (true) {
            System.out.println("\n=== День " + currentDay + " ===");
            displayCatTable();

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
                performAction(choice);
                break;
            case "a":
                addNewCat();
                break;
            case "n":
                nextDay();
                break;
            case "s":
                sortCats();
                break;
            default:
                System.out.println("Неверный ввод, попробуйте еще раз");
        }
    }

    private void performAction(String action) {
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

    private void addNewCat() {
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

    private void nextDay() {
        currentDay++;
        System.out.println("\n=== Наступил день " + currentDay + " ===");
        cats.forEach(Cat::nextDay);
    }

    private void displayCatTable() {
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

    private void sortCats() {
        System.out.println("\nСортировать по:");
        System.out.println("1: Имени");
        System.out.println("2: Возрасту");
        System.out.println("3: Здоровью");
        System.out.println("4: Настроению");
        System.out.println("5: Сытости");
        System.out.println("6: Среднему уровню");
        System.out.print("Выберите критерий: ");

        String choice = scanner.nextLine().trim();
        Comparator<Cat> comparator;

        switch (choice) {
            case "1":
                comparator = Comparator.comparing(Cat::getName);
                break;
            case "2":
                comparator = Comparator.comparingInt(Cat::getAge);
                break;
            case "3":
                comparator = Comparator.comparingInt(Cat::getHealth).reversed();
                break;
            case "4":
                comparator = Comparator.comparingInt(Cat::getMood).reversed();
                break;
            case "5":
                comparator = Comparator.comparingInt(Cat::getSatiety).reversed();
                break;
            case "6":
                comparator = Comparator.comparingDouble(Cat::getAverageLifeLevel).reversed();
                break;
            default:
                System.out.println("Неверный выбор, сортировка не изменена");
                return;
        }

        cats.sort(comparator);
        System.out.println("Сортировка применена!");
    }

    private void initializeSampleCats() {
        cats.add(new Cat("Peach", 11, 78, 86, 50));
        cats.add(new Cat("Seth", 9, 94, 53, 30));
        cats.add(new Cat("Jasper", 12, 83, 39, 43));
        cats.add(new Cat("Poppy", 9, 38, 57, 71));
    }

    private void saveCats() {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(cats, writer);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private void loadCats() {
        File file = new File(SAVE_FILE);
        if (!file.exists()) return;

        try (FileReader reader = new FileReader(SAVE_FILE)) {
            Type listType = new TypeToken<ArrayList<Cat>>(){}.getType();
            List<Cat> loadedCats = gson.fromJson(reader, listType);
            if (loadedCats != null) {
                cats.addAll(loadedCats);
            }
        } catch (IOException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }
    }
}