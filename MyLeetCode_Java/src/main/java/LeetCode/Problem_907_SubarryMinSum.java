package LeetCode;

import java.util.ArrayDeque;

// IMP: 单调栈, 非常非常重要!!

public class Problem_907_SubarryMinSum {

    private static final int mod = (int) 1e9 + 7;

    public static int sumSubarrayMins(int[] arr) {
        int[] left = nearLessEqualLeft(arr);
        int[] right = nearLessRight(arr);
        long ans = 0; // 注意越界, 用long
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * (long) arr[i];
            ans %= mod;
        }
        return (int) ans;
    }

    // 找到左边离自己最近的<=自己的: 开头算局部
    public static int[] nearLessEqualLeft(int[] arr) {
        int N = arr.length;
        int[] left = new int[N];
        // stack里头存的是下标
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        return left;
    }

    // 找到右边离自己最近的<自己的: 结尾算区间的最大可用
    public static int[] nearLessRight(int[] arr) {
        int N = arr.length;
        int[] right = new int[N];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = N;
        }
        return right;
    }
}
