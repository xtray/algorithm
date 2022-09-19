package _LintCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_407_PlusOne {

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int N = digits.length;
        int idx = N - 1;
        int carry = 0;
        while (idx >= 0) {
            if (digits[idx] != 9) {
                // 最后一位加carry
                digits[idx] += idx == N - 1 ? 1 : carry;
                return digits;
            } else {
                digits[idx] = 0;
                carry = 1;
            }
            idx--;
        }
        int[] ans = new int[N + 1];
        ans[0] = 1;
        return ans;
    }

    public int[] plusOne2(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int N = digits.length;
        int idx = N - 1;
        int carry = 1;
        while (idx >= 0) {
            if (digits[idx] != 9) {
                // 最后一位加carry
                digits[idx] += carry;
                return digits;
            } else {
                digits[idx] = 0;
                // carry = 1;
            }
            idx--;
        }
        int[] ans = new int[N + 1];
        ans[0] = 1;
        return ans;
    }

    public int[] plusOne1(int[] digits) {
        if (digits == null || digits.length == 0) {
            return new int[0];
        }
        int N = digits.length;
        int carray = 1; // 加的那个1
        for (int i = N - 1; i >= 0; i--) { // 低位到高位
            int cur = digits[i] + carray;
            digits[i] = cur % 10;
            carray = cur / 10;
        }
        int[] ans = new int[N + carray];
        for (int i = 0, j = carray; i < N; i++, j++) {
            ans[j] = digits[i];
        }
        if (carray > 0) ans[0] = 1;
        return ans;
    }

    public static void main(String[] args) {
        int[] digits = new int[]{9,9};
        var ans = new Problem_407_PlusOne().plusOne(digits);
        System.out.println(Arrays.toString(ans));
    }
}
