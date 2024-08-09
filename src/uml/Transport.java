package uml;

import java.util.Random;

public abstract class Transport {
    protected int name;
    protected String stationName;
    protected int fare;

    public abstract boolean ride(Person person);
    public abstract void transfer();
    public abstract void getOff();

    public int getFare() {
        return fare;
    }
    
   
    public static Transport selectTransport(int timeDiff, String destination) {
        Random random = new Random();
        int r = random.nextInt(1000);

        Transport vehicle = null;
        if (timeDiff <= 20) {
            vehicle = new Taxi(r, destination);
        } else if (timeDiff <= 40) {
            vehicle = new Bus(r, destination);
        } else if (timeDiff <= 60) {
            vehicle = new Subway(r, destination);
        } else {
            System.out.println("늦었습니다.");
        }
        return vehicle;
    }
}
