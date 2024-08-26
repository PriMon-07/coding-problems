package general;

public class MyClass {

    static int a = 20;
    static int b = 30;
    static int c = 40;

    MyClass() {
        a = 200;
    }

    static void m1() {
        b = 300;
    }

    static {
        c = 400;  // Fixed the incorrect syntax
    }

    public static void main(String[] args) {
        System.out.println(a);  // Prints 20
        System.out.println(b);  // Prints 30
        System.out.println(c);  // Prints 400
    }
}