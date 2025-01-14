package uml;

public class Person {
    private int currentTime;
    private int targetTime;
    private int money;
    private int tardinessCount;

    public Person(int currentTime, int targetTime, int money) {
        this.currentTime = currentTime;
        this.targetTime = targetTime;
        this.money = money;
        this.tardinessCount = 0;
    }

    public int diffTime() {
        return this.targetTime - this.currentTime;
    }

    public int getMoney() {
        return money;
    }

    public void deductMoney(int amount) {
        this.money -= amount;
    }

    public void incrementTardiness() {
        this.tardinessCount++;
        if (this.tardinessCount >= 3) {
            System.out.println("노래 부르기");
        }
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public void setTargetTime(int targetTime) {
        this.targetTime = targetTime;
    }
}