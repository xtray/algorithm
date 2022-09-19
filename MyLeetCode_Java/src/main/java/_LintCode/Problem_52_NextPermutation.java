package _LintCode;

import java.util.Arrays;

public class Problem_52_NextPermutation {

    public int[] nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int N = nums.length;
        int i = N - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }
        if (i < 0) { // 逆序
            reverse(nums, 0, N-1);
            return nums;
        }
        // i+1..N-1找到第一个比nums[i]大的
        for (int j = N - 1; j > i; j--) {
            if (nums[j] > nums[i]) {
                swap(nums, i, j);
                break;
            }
        }
        // i+1~N-1逆序
        reverse(nums, i+1, N-1);
        return nums;
    }

    private static void reverse(int[] nums, int L, int R) {
        while (L < R) {
            swap(nums, L++, R--);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 7, 3, 2, 5, 1};
        int[] nums = {4, 3, 2, 1};
        var ans = new Problem_52_NextPermutation().nextPermutation(nums);
        System.out.println(Arrays.toString(ans));
    }
}
