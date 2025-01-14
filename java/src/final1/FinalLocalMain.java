package final1;

public class FinalLocalMain {
    public static void main(String[] args) {
        // final 지역 변수
        final int data1;
        data1 = 10;

        // final 지역 변수2
        final int data2 = 10;
        method(10);
    }

    static void method(final int parameter) {

    }
}
