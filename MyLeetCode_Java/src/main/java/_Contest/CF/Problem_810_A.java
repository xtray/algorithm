package _Contest.CF;

import java.util.Scanner;

// https://codeforces.com/contest/1711/problem/A
// https://codeforces.com/problemset/problem/1711/A

public class Problem_810_A {

    int getPerfectPermutation(int n) {
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for (int i = 0; i < times; i++) {
            int n = sc.nextInt();
            var ans = new Problem_810_A().getPerfectPermutation(n);
            System.out.println(ans);
        }
        sc.close();
    }
}
