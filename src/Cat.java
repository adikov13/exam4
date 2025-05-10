public class Cat {
    private String name;
    private int age;
    private int health;
    private int mood;
    private int satiety;

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

    // Метод для расчета среднего уровня жизни
    public double getAverageLifeLevel() {
        return (health + mood + satiety) / 3.0;
    }

    // Методы взаимодействия
    public void feed(int amount) {
        setSatiety(satiety + amount);
    }

    public void play(int amount) {
        setMood(mood + amount);
        setSatiety(satiety - amount / 2); // Игра уменьшает сытость
    }

    public void heal(int amount) {
        setHealth(health + amount);
    }

    // Сеттеры с валидацией
    public void setAge(int age) {
        if (age < 1 || age > 18) {
            throw new IllegalArgumentException("Возраст должен быть от 1 до 18");
        }
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