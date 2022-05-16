package _aTemplate.IO;

import java.io.BufferedInputStream;
import java.util.Scanner;

// IMP: 重要读取模板

public class SimpleInput {

    public static void main(String[] args) {
        long t1, t2;
        Scanner sc1 = new Scanner(System.in);
        // sc2用到缓冲流，读入更快
        Scanner sc2 = new Scanner(new BufferedInputStream(System.in));

        t1 = System.nanoTime();
        String input1 = sc1.next();
        t2 = System.nanoTime();

        System.out.println("sc1:" + (t2 - t1));
        System.out.println(input1);

        //////////

        t1 = System.nanoTime();
        String input2 = sc2.next();
        t2 = System.nanoTime();

        System.out.println("sc2:" + (t2 - t1));
        System.out.println(input2);
    }

}
