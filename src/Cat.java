import java.util.Random;

public class Cat {
    private String name;
    private int age;
    private int health;
    private int mood;
    private int satiety;
    private boolean actionPerformedToday;
    private static final Random random = new Random();

    // Конструктор для GSON
    public Cat() {}

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
        this.actionPerformedToday = false;
    }

    // Геттеры и сеттеры (необходимы для GSON)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age < 1 || age > 18) throw new IllegalArgumentException("Возраст должен быть от 1 до 18");
        this.age = age;
    }

    public int getHealth() { return health; }
    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(100, health));
    }

    public int getMood() { return mood; }
    public void setMood(int mood) {
        this.mood = Math.max(0, Math.min(100, mood));
    }

    public int getSatiety() { return satiety; }
    public void setSatiety(int satiety) {
        this.satiety = Math.max(0, Math.min(100, satiety));
    }

    public boolean isActionPerformedToday() { return actionPerformedToday; }
    public void setActionPerformedToday(boolean actionPerformedToday) {
        this.actionPerformedToday = actionPerformedToday;
    }

    // Методы взаимодействия
    public void feed() {
        if (actionPerformedToday) {
            System.out.println(name + " уже взаимодействовал сегодня!");
            return;
        }

        if (random.nextInt(100) < 10) { // 10% chance of poisoning
            int decrease = getDecreaseStep() * 2;
            setMood(mood - decrease);
            setHealth(health - decrease);
            System.out.println("О нет! " + name + " отравился!");
        } else {
            int step = getIncreaseStep();
            setSatiety(satiety + step);
            setMood(mood + step/2);
            System.out.printf("%s покормлен! Сытость +%d, Настроение +%d%n", name, step, step/2);
        }
        actionPerformedToday = true;
    }

    public void play() {
        if (actionPerformedToday) {
            System.out.println(name + " уже взаимодействовал сегодня!");
            return;
        }

        if (random.nextInt(100) < 5) { // 5% chance of injury
            int decrease = getDecreaseStep() * 2;
            setMood(mood - decrease);
            setHealth(health - decrease);
            System.out.println("О нет! " + name + " травмировался во время игры!");
        } else {
            int increase = getIncreaseStep();
            int decrease = getDecreaseStep();
            setMood(mood + increase);
            setHealth(health + increase/2);
            setSatiety(satiety - decrease);
            System.out.printf("Поиграли с %s! Настроение +%d, Здоровье +%d, Сытость -%d%n",
                    name, increase, increase/2, decrease);
        }
        actionPerformedToday = true;
    }

    public void heal() {
        if (actionPerformedToday) {
            System.out.println(name + " уже взаимодействовал сегодня!");
            return;
        }

        int increase = getIncreaseStep();
        int decrease = getDecreaseStep();
        setHealth(health + increase);
        setMood(mood - decrease);
        setSatiety(satiety - decrease);
        System.out.printf("%s у ветеринара! Здоровье +%d, Настроение -%d, Сытость -%d%n",
                name, increase, decrease, decrease);
        actionPerformedToday = true;
    }

    public void nextDay() {
        actionPerformedToday = false;

        // Random changes
        setSatiety(satiety - (random.nextInt(5) + 1));
        setMood(mood + random.nextInt(7) - 3); // -3 to +3
        setHealth(health + random.nextInt(7) - 3); // -3 to +3

        // Check if cat died
        if (health <= 0) {
            System.out.println("К сожалению, " + name + " умер...");
        }
    }

    public boolean isAlive() {
        return health > 0;
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

    public double getAverageLifeLevel() {
        return (health + mood + satiety) / 3.0;
    }
}