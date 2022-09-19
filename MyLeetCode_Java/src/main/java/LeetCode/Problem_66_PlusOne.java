package LeetCode;

import java.util.Arrays;

public class Problem_66_PlusOne {

    // 直接把结果处理到digits数组返回, 如果digits当前位不是9, 直接+1返回, 如果是9, 置0,carry=1
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int N = digits.length;
        int idx = N - 1;
        int carry = 0;
        while (idx >= 0) {
            if (digits[idx] != 9) {
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
        int[] digits = new int[]{9, 9};
        var ans = new Problem_66_PlusOne().plusOne(digits);
        System.out.println(Arrays.toString(ans));
    }
}
