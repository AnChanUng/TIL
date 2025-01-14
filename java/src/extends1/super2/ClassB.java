package extends1.super2;

public class ClassB extends ClassA {

    public ClassB(int a) {
        this(a, 0); // 생략가능
        System.out.println("classB 생성자 a=" + a);
    }

    public ClassB(int a, int b) {
        super();
        System.out.println("classB 생성자 a="  + a + " b=" + b);
    }

}