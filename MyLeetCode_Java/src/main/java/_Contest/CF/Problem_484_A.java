package _Contest.CF;

// https://codeforces.com/problemset/problem/484/A

import java.util.Scanner;

/**
 * 输入 t (t≤1e4) 表示 t 组数据，每组数据输入两个数 L 和 R (0≤L≤R≤1e18)。
 * <p>
 * 对每组数据，输出在区间 [L,R] 内的二进制中的 1 的个数最多的那个数字，如果有多个这样的数字，输出最小的那个。
 * <p>
 * 输入
 * 3
 * 1 2
 * 2 4
 * 1 10
 * 输出
 * 1
 * 3
 * 7
 */

public class Problem_484_A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for (int t = 0; t < times; t++) {
            long L = sc.nextLong();
            long R = sc.nextLong();
            while ((L | (L + 1)) <= R) {
                L |= L + 1; // IMP: 把最低的 0 改成 1
            }
            System.out.println(L);
        }
        sc.close();
    }


}
