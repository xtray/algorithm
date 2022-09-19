package _aTemplate.IO;

import java.util.Arrays;
import java.util.Scanner;

public class ReadString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine(); // 一次读取一行
        // String s = "abc123abc1a3a1";
        System.out.print(s);
    }

    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //     String str = sc.next(); // 获取字符串类型
    //
    //     String[] split = str.split(",");
    //     int N = split.length;
    //
    //     int[] nums = new int[N];
    //     for (int i = 0; i < N; i++) {
    //         nums[i] = Integer.parseInt(split[i]);
    //     }
    //
    //     System.out.println(Arrays.toString(nums));
    //     sc.close();
    // }

}
