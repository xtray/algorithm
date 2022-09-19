package _aTemplate.IO;

import java.util.Scanner;

public class ReadInt {

    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     int N = sc.nextInt();
    //     int K = sc.nextInt();
    //     sc.close();
    // }

    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     long N = sc.nextLong();
    //     long K = sc.nextLong();
    //     sc.close();
    // }

    // 当我们牛客网在线编程时 while(sc.hasNext()) 可以自己跳出循环，
    // 而在本地IDEA中使用while(sc.hasNext())是没有办法退出循环
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     while (sc.hasNext()) {
    //         int m = sc.nextInt();
    //         int n = sc.nextInt();
    //         System.out.printf("m: %s, n: %s.\n", m, n);
    //     }
    //     sc.close();
    // }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext("quit")) { // 跳出循环
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.printf("m: %s, n: %s.\n", m, n);
        }
        sc.close();
    }
}
