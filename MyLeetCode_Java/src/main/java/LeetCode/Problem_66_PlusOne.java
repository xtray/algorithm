package LeetCode;

public class Problem_66_PlusOne {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        int N = digits.length;
        int idx = N -1;
        int carry = 0;
        while (idx>=0) {
            if(digits[idx] != 9) {
                digits[idx] += idx == N-1? 1: carry;
                return digits;
            } else {
                digits[idx] = 0;
                carry = 1;
            }
            idx--;
        }
        int[] ans = new int[N+1];
        ans[0] = 1;
        return ans;
    }
}
