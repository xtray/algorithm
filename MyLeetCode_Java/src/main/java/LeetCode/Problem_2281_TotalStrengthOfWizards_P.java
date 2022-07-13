package LeetCode;

import java.util.Stack;

// PENDING

// https://leetcode.cn/problems/sum-of-total-strength-of-wizards/
// PENDING: 前缀和 + 单调栈

// ref: Problem_1856_MaxSumMinProduct
// ref: Problem_907_SubarryMinSum: 子数组的最小值之和

public class Problem_2281_TotalStrengthOfWizards_P {

    private static final int mod = (int) 1e9 + 7;

    // 以每一个i位置为最弱
    public int totalStrength(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        long ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                long cur = (long) (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j];
                ans += cur;
                ans %= mod;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            long cur = (long) (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j];
            ans += cur;
            ans %= mod;
        }
        return (int) ans;
    }

    public static int totalStrength2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }

        int[] stack = new int[arr.length];
        int[] left = nearLessEqualLeft(arr, stack);
        int[] right = nearLessRight(arr, stack);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;

            ans += start * end * (long) arr[i];
            ans %= 1000000007;
        }
        return (int) ans;
    }

    public static int[] nearLessEqualLeft(int[] arr, int[] stack) {
        int N = arr.length;
        int[] left = new int[N];
        int size = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (size != 0 && arr[i] <= arr[stack[size - 1]]) {
                left[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            left[stack[--size]] = -1;
        }
        return left;
    }

    public static int[] nearLessRight(int[] arr, int[] stack) {
        int N = arr.length;
        int[] right = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size != 0 && arr[stack[size - 1]] > arr[i]) {
                right[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        while (size != 0) {
            right[stack[--size]] = N;
        }
        return right;
    }
}
