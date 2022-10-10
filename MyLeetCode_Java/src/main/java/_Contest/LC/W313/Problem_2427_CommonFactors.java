package _Contest.LC.W313;

import java.util.*;

public class Problem_2427_CommonFactors {

    public int commonFactors(int a, int b) {

        Set<Integer> set = new HashSet<>();
        for (int j = 1; j * j <= a; j++) { // j是现在试的因子
            if (a % j == 0) { // num含有j的因子
                set.add(j);
                int other = a / j; // other * j == num
                if (other != 1) {
                    set.add(other);
                }
            }
        }
        int cnt = 0;
        for (int j = 1; j * j <= b; j++) { // j是现在试的因子
            if (b % j == 0) { // num含有j的因子
                if (set.contains(j)) {
                    cnt++;
                }
                int other = b / j; // other * j == num
                if (other != 1 && set.contains(other)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        // int a = 25;
        // int b = 30; // 2
        int a = 1;
        int b = 1; // 2
        var ans = new Problem_2427_CommonFactors().commonFactors(a, b);
        System.out.println(ans);
    }
}
