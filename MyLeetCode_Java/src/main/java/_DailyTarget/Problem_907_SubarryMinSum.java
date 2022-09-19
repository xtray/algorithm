package _DailyTarget;

import java.util.ArrayDeque;

// IMP: 单调栈, 非常非常重要!!

public class Problem_907_SubarryMinSum {

    private static final int mod = (int) 1e9 + 7;

    // 单调栈
    public int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            // 栈底最小,  小-->大
            // 每个子数组的最小值: 找离i位置最近的< i的数
            while (!queue.isEmpty() && arr[i] < arr[queue.peekLast()]) {
                // 结算栈顶
                int top = queue.pollLast();
                int L = queue.isEmpty() ? -1 : queue.peekLast();
                int R = i;
                ans = (ans + ((long) (top - L) * (R - top) * arr[top]) % mod) % mod;
            }
            queue.addLast(i);
        }
        while (!queue.isEmpty()) {
            int top = queue.pollLast();
            int L = queue.isEmpty() ? -1 : queue.peekLast();
            int R = N;
            ans = (ans + ((long) (top - L) * (R - top) * arr[top]) % mod) % mod;
        }
        return (int) ans;
    }


    public static int sumSubarrayMins1(int[] arr) {
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

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 4};
        var ans = new Problem_907_SubarryMinSum().sumSubarrayMins(arr);
        System.out.println(ans);
    }
}
