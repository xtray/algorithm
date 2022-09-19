package _Test;

public class TestStatic {

    /**
     * 程序执行了一次 new TestB() ，由于 TestB 类继承了 TestA 类，程序会先去执行 TestA 类的静态代码块，
     * 然后执行 TestB 类的静态代码块，然后执行 TestA 类的构造代码块和构造方法，
     * 最后执行 TestB 类的构造代码块和构造方法。
     */

    static class A {
        static {
            System.out.println("A 静态块>>>>>");
        }

        {
            System.out.println("A 构造块>>>>>");
        }

        A() {
            System.out.println("A 无参构造方法>>>>>");
        }

        A(int a) {
            System.out.println("A 有参构造方法>>>>>: " + a);
        }
    }

    static class B extends A {
        static {
            System.out.println("B 静态块>>>>>");
        }

        {
            System.out.println("B 构造块>>>>>");
        }

        B() {
            System.out.println("B 无参构造方法>>>>>");
        }

        B(int a) {
            // super(a);
            System.out.println("B 有参构造方法>>>>>: " + a);
        }
    }

    public static void main(String[] args) {
         B test = new B();
        System.out.println("+++++++++++++");
         B test2 = new B(1);
    }
}
