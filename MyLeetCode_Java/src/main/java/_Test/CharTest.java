package _Test;

public class CharTest {

    public static class TestClass {
        public char cmp;
        public TestClass() {

        }
    }

    public static void main(String[] args) {
        char tst = '\u0000';
        TestClass tc = new TestClass();
        if(tc.cmp == tst) {
            System.out.println("Equal");
        } else {
            System.out.println("NG");
        }

    }
}
