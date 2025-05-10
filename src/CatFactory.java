import java.util.Scanner;

public class CatFactory {
    public static Cat createCat(Scanner scanner) {
        String name = getName(scanner);
        if (name == null) return null;

        Integer age = getAge(scanner);
        if (age == null) return null;

        return new Cat(name, age);
    }

    private static String getName(Scanner scanner) {
        System.out.print("Введите имя кота: ");
        String name = scanner.nextLine().trim();
        return name.isEmpty() ? null : name;
    }

    private static Integer getAge(Scanner scanner) {
        System.out.print("Введите возраст кота (1-18): ");
        try {
            int age = Integer.parseInt(scanner.nextLine());
            return (age >= 1 && age <= 18) ? age : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}