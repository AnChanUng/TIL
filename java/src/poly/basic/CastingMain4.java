package poly.basic;

public class CastingMain4 {

    public static void main(String[] args) {
        Child parent1 = new Child();
        Child child1 = (Child) parent1;
        child1.childMethod(); // 문제 없음

//        Parent parent2 = new Child();
//        Child child2 = (Child) parent2; // 런타임오류
//        child2.childMethod();


    }

}
