package uml;

public class Subway extends Transport {

    public Subway(int name, String destination) {
        this.name = name;
        Station s = new Station(destination);
        this.stationName = s.getStationName();
        this.fare = 1500; // 지하철 요금
    }

    @Override
    public boolean ride(Person person) {
        if (person.getMoney() >= fare) {
            person.deductMoney(fare);
            System.out.println(name + " 지하철에 탑승합니다. 잔액: " + person.getMoney() + "원");
            return true;
        } else {
            System.out.println("잔액이 부족합니다.");
            return false;
        }
    }

    @Override
    public void transfer() {
        System.out.println(name + " 지하철로 환승합니다.");
    }

    @Override
    public void getOff() {
        System.out.println(stationName + " " + name + " 지하철에서 내립니다.");
    }
}