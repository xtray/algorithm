package LeetCode;

import java.util.Arrays;

public class Problem_1748_SumOfUnique {

    // O(N*logN)
    public int sumOfUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int L = 0;
        int R = 0;
        int N = nums.length;
        int ans = 0;
        while (L < N) {
            while (R < N && nums[R] == nums[L]) {
                R++;
            }
            if (R - L == 1) {
                ans += nums[L];
            }
            L = R;
        }
        return ans;
    }

    // O(N + V)
    // V: 101
    public int sumOfUnique2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        int ans = 0;
        for (int i = 1; i <= 100; i++) {
            if (cnt[i] == 1) {
                ans += i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int times = 10000;
        int maxSize = 100;
        int maxVal = 100;
        System.out.println("测试开始");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxSize, maxVal);
            var ans1 = new Problem_1748_SumOfUnique().sumOfUnique(arr);
            var ans2 = new Problem_1748_SumOfUnique().sumOfUnique2(arr);
            if (ans1 != ans2) {
                System.out.println("Oooops!!");
                break;
            }
        }
        System.out.println("测试结束");
    }

    // maxSize:[1,100]
    // maxVal:[1,100]
    private static int[] generateRandomArray(int maxSize, int maxVal) {
        int size = (int) (Math.random() * maxSize) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * maxVal) + 1;
        }
        return arr;
    }
}
