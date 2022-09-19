package _Test;

class ATest {
    public static int a = 100;

    static {
        System.out.println("is test A");
    }
}

class BTest extends ATest {
    public static int b = 200;

    static {
        System.out.println("is test B");
    }
}

public class CheckStatic {
    public static void main(String[] args) {

        System.out.println(BTest.a);

    }
}