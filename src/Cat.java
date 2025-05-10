import java.util.Random;

public class Cat {
    private String name;
    private int age;
    private int health;
    private int mood;
    private int satiety;
    private static final Random random = new Random();

    public Cat(String name, int age) {
        this(name, age,
                random.nextInt(61) + 20, // health 20-80
                random.nextInt(61) + 20, // mood 20-80
                random.nextInt(61) + 20); // satiety 20-80
    }

    public Cat(String name, int age, int health, int mood, int satiety) {
        this.name = name;
        setAge(age);
        setHealth(health);
        setMood(mood);
        setSatiety(satiety);
    }

    // Геттеры
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getHealth() { return health; }
    public int getMood() { return mood; }
    public int getSatiety() { return satiety; }

    public double getAverageLifeLevel() {
        return (health + mood + satiety) / 3.0;
    }

    // Методы взаимодействия с учетом возраста
    public void feed() {
        int step = getIncreaseStep();
        setSatiety(satiety + step);
        setMood(mood + step/2);
        System.out.printf("%s покормлен! Сытость +%d, Настроение +%d%n", name, step, step/2);
    }

    public void play() {
        int increase = getIncreaseStep();
        int decrease = getDecreaseStep();
        setMood(mood + increase);
        setHealth(health + increase/2);
        setSatiety(satiety - decrease);
        System.out.printf("Поиграли с %s! Настроение +%d, Здоровье +%d, Сытость -%d%n",
                name, increase, increase/2, decrease);
    }

    public void heal() {
        int increase = getIncreaseStep();
        int decrease = getDecreaseStep();
        setHealth(health + increase);
        setMood(mood - decrease);
        setSatiety(satiety - decrease);
        System.out.printf("%s у ветеринара! Здоровье +%d, Настроение -%d, Сытость -%d%n",
                name, increase, decrease, decrease);
    }

    private int getIncreaseStep() {
        if (age <= 5) return 7;
        if (age <= 10) return 5;
        return 4;
    }

    private int getDecreaseStep() {
        if (age <= 5) return 3;
        if (age <= 10) return 5;
        return 6;
    }

    // Сеттеры с валидацией
    public void setAge(int age) {
        if (age < 1 || age > 18) throw new IllegalArgumentException("Возраст должен быть от 1 до 18");
        this.age = age;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(100, health));
    }

    public void setMood(int mood) {
        this.mood = Math.max(0, Math.min(100, mood));
    }

    public void setSatiety(int satiety) {
        this.satiety = Math.max(0, Math.min(100, satiety));
    }
}