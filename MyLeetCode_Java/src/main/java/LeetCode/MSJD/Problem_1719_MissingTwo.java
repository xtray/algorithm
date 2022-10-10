package LeetCode.MSJD;

import java.util.Arrays;

public class Problem_1719_MissingTwo {

    public int[] missingTwo(int[] nums) {
        int N = nums.length;
        int idx = 0;
        int[] ans = new int[2];
        int a = 0; // N-2
        int b = 0; // N-1
        // i位置放i+1
        // nums[i] -> nums[i] - 1
        // nums[i] -> i + 1
        for (int i = 0; i < N; ) {
            if (nums[i] > N) {
                if (nums[i] == N + 1) {
                    a = N + 1;
                } else {
                    b = N + 2;
                }
                i++;
            } else if (nums[i] != i + 1) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != i + 1) {
                ans[idx++] = i + 1;
            }
        }
        if (a == 0) {
            ans[idx++] = N + 1;
        }
        if (b == 0) {
            ans[idx] = N + 2;
        }

        return ans;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int[] missingTwo1(int[] nums) {
        int N = nums.length;
        int sum = (1 + N + 2) * (N + 2) / 2;
        for (int num : nums) sum -= num;
        int mid = sum >> 1; // 一个数 <=mid, 一个 > mid
        int midSum = (1 + mid) * mid / 2;
        for (int num : nums) {
            if (num <= mid) midSum -= num;
        }
        return new int[]{midSum, sum - midSum};
    }


    // https://leetcode.cn/problems/missing-two-lcci/solution/by-lcbin-jpie/
    public int[] missingTwo2(int[] nums) {
        int N = nums.length;
        int xor = 0;
        for (int i = 1; i <= N + 2; i++) xor ^= i;
        for (int num : nums) xor ^= num;
        int rightMostOne = xor & (-xor);
        int a = 0;
        // 1~N+2异或, 数组里所有数再异或, 相当于每个数出现两次, 留下的是出现一次的
        for (int i = 1; i <= N + 2; i++) {
            if ((i & rightMostOne) != 0) {
                a ^= i;
            }
        }
        for (int num : nums) {
            if ((num & rightMostOne) != 0) {
                a ^= num;
            }
        }
        return new int[]{a, xor ^ a};
    }

    public static void main(String[] args) {
        int[] nums = {1, 3};
        var ans = new Problem_1719_MissingTwo().missingTwo(nums);
        System.out.println(Arrays.toString(ans));
    }
}
