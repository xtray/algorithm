package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_2396_PalindromicNumber {

    public boolean isStrictlyPalindromic(int n) {
        if (n < 4) return false;
        // 2 ~ n-2进制
        for (int digit = n - 2; digit >= 2; digit--) {
            if (!check(n, digit)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(int n, int k) {
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % k);
            n /= k;
        }
        // 检查
        int L = 0;
        int R = list.size() - 1;
        while (L < R) {
            if (!list.get(L).equals(list.get(R))) {
                return false;
            }
            L++;
            R--;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 9;
        var ans = new Problem_2396_PalindromicNumber().isStrictlyPalindromic(n);
        System.out.println(ans);
    }
}
