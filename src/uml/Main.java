package uml;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        // 초기 Person 객체 생성
        System.out.print("현재 가지고 있는 돈을 입력해 주세요 : ");
        int initialMoney = sc.nextInt();
        
        // 사용자 입력을 받을 때마다 갱신할 Person 객체 생성
        Person person = new Person(0, 0, initialMoney);

        while (true) {
        	System.out.print("출발 시간을 입력해 주세요 (예: 1시 30분): ");
            sc.nextLine();
            String startTime = sc.nextLine();
            System.out.print("도착 시간을 입력해 주세요 (예: 2시 45분): ");
            String endTime = sc.nextLine();
            System.out.print("목적지를 입력해 주세요 : ");
            String destination = sc.nextLine();

            int startMinutes = convertToMinutes(startTime);
            int endMinutes = convertToMinutes(endTime);

            person.setCurrentTime(startMinutes);
            person.setTargetTime(endMinutes);

            int timeDiff = person.diffTime();
            Transport vehicle = Transport.selectTransport(timeDiff, destination);

            if (vehicle != null) {
                boolean canRide = vehicle.ride(person);
                if (canRide) {
                    vehicle.getOff();
                } else {
                    person.incrementTardiness();
                }
            } else {
                person.incrementTardiness();
            }

            System.out.print("더 입력하시겠습니까? (y/n): ");
            String response = sc.next();     
            if (response.equalsIgnoreCase("n")) {
                break;
            }
        }

        sc.close();
    }
    
    private static int convertToMinutes(String time) {
        int hourIndex = time.indexOf("시");
        int minuteIndex = time.indexOf("분");

        if (hourIndex == -1 || minuteIndex == -1 || hourIndex >= minuteIndex) {
            throw new IllegalArgumentException("잘못된 시간 형식입니다: " + time);
        }

        int hours = Integer.parseInt(time.substring(0, hourIndex).trim());
        int minutes = Integer.parseInt(time.substring(hourIndex + 1, minuteIndex).trim());

        return hours * 60 + minutes;
    }
}
