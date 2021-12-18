package _Test;

public class BitOP {

    public static void printBin(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) != 0 ? 1 : 0);
        }
        System.out.println();
    }

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    private static int fillOnes(int len) {
        return -1 >>> (32 -len);
    }

    public static void main(String[] args) {
//        int num = 0;
//        int ans1 = (num - 1) >> 1;
//        System.out.println(ans1);
//        int ans2 = (num - 1) / 2;
//        System.out.println(ans2);
//        int ans3 = num * 2 + 1;
//        int ans4 = num << 1 | 1;
//        System.out.println(ans3);
//        System.out.println(ans4);
//        printBin(1);
//        printBin(-1);
//        print(-1);
//        printBin(2);
//        print(-1>>>1);
        for(int i=1;i<=32;i++){
            print(fillOnes(i));
        }
    }
}
