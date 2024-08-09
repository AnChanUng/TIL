package poly.basic;

public class PolyMain {
    public static void main(String[] args) {
        System.out.println("Parent -> Parent");
        Parent parent = new Parent();
        parent.parentMethod();

        System.out.println("Child -> child");
        Child child = new Child();
        child.parentMethod();
        child.childMethod();

        System.out.println("Parent -> child");
        Parent poly = new Child();
        poly.parentMethod();

        //Child child1 = new Parent();
        //자식의 기능은 호출할 수 없다.
        poly.parentMethod();
    }
}
