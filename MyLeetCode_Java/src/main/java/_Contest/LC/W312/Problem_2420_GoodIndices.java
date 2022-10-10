package _Contest.LC.W312;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Problem_2420_GoodIndices {

    public List<Integer> goodIndices(int[] nums, int k) {

        List<Integer> ans = new ArrayList<>();
        int N = nums.length;
        // i 位置右侧<nums[i]的位置
        // i 位置左侧<nums[i]的位置
        int[] left = nearLessLeft(nums);
        int[] right = nearLessRight(nums);
        for (int i = k; i < N - k; i++) {
            int lenL = i - 1 - left[i - 1];
            int lenR = right[i + 1] - i - 1;
            if (lenL >= k && lenR >= k) {
                ans.add(i);
            }
        }
        return ans;
    }


    // 找到左边离自己最近的<自己的
    public static int[] nearLessLeft(int[] arr) {
        int N = arr.length;
        int[] left = new int[N];
        // stack里头存的是下标
        // 栈底->栈顶 由小到大
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peekLast()]) {
                left[stack.pollLast()] = i;
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pollLast()] = -1;
        }
        int maxVal = left[0];
        for (int i = 0; i<N; i++) {
            maxVal = Math.max(maxVal, left[i]);
            left[i] = maxVal;
        }
        return left;
    }

    // 找到右边离自己最近的<自己的
    // 栈顶最小--> 大
    public static int[] nearLessRight(int[] arr) {
        int N = arr.length;
        int[] right = new int[N];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peekLast()]) {
                int top = stack.pollLast();
                right[top] = i;
            }
            stack.addLast(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pollLast()] = N;
        }
        int minVal = right[N-1];
        for (int i = N-2; i>=0; i--) {
            minVal = Math.min(minVal, right[i]);
            right[i] = minVal;
        }
        return right;
    }

    public static void main(String[] args) {
        // int[] nums = {2, 1, 1, 1, 3, 4, 1};
        // int k = 2;
        int[] nums = {433, 639679, 267108, 15179, 14818, 10492, 8478, 3160, 2340, 1506, 1168, 1167, 600, 523, 152, 132, 111, 51, 736058, 237085, 943608, 997519, 997796, 998643, 999914, 999922, 999947, 999971, 999993, 999997};
        int k = 10; // 18
        var ans = new Problem_2420_GoodIndices().goodIndices(nums, k);
        System.out.println(ans);
    }
}
