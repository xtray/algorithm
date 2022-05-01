package LeetCode.Contest.W288;

import java.util.ArrayList;
import java.util.List;

public class Problem_6037_LargestInteger {

    public int largestInteger(int num) {
        if (num < 9) {
            return num;
        }
        int[] digit = getDigits(num);
        List<Integer> oddList = new ArrayList<>();
        List<Integer> oddPos = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        List<Integer> evenPos = new ArrayList<>();
        for (int i = 0; i < digit.length; i++) {
            if ((digit[i] & 1) != 0) {
                oddList.add(digit[i]);
                oddPos.add(i);
            } else {
                evenList.add(digit[i]);
                evenPos.add(i);
            }
        }
        oddList.sort((o1, o2) -> o2 - o1);
        evenList.sort((o1, o2) -> o2 - o1);
        int idx = 0;
        for (int pos : oddPos) {
            digit[pos] = oddList.get(idx++);
        }
        idx = 0;
        for (int pos : evenPos) {
            digit[pos] = evenList.get(idx++);
        }
        int res = 0;
        for (int d : digit) {
            res = res * 10 + d;
        }
        return res;
    }

    private int[] getDigits(int num) {
        int N = getLen(num);
        int[] digits = new int[N];
        for (int i = 0; i < N; i++) {
            digits[N - 1 - i] = num % 10;
            num /= 10;
        }
        return digits;
    }

    private int getLen(int num) {
        int len = 0;
        while (num != 0) {
            len++;
            num /= 10;
        }
        return len;
    }

    public static void main(String[] args) {
        int num = (int)1e9 ;
        // int num = 1234;
        // int num = 1234;
        var ans = new Problem_6037_LargestInteger().largestInteger(num);
        System.out.println(ans);
    }
}
