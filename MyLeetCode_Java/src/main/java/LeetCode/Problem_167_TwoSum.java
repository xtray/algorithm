package LeetCode;

public class Problem_167_TwoSum {

    // O(N)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return new int[]{-1, -1};
        }
        int L = 0;
        int R = nums.length - 1;
        while (L < R) {
            int sum = nums[L] + nums[R];
            if (sum < target) {
                L++;
            } else if (sum > target) {
                R--;
            } else {
                return new int[]{L + 1, R + 1};
            }
        }
        return new int[]{-1, -1};
    }

    // 二分, O(N*logN)
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null || nums.length <= 1) {
            return new int[]{-1, -1};
        }
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            int aim = target - nums[i];
            int L = i + 1;
            int R = N - 1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (nums[mid] > aim) {
                    R = mid - 1;
                } else if (nums[mid] < aim) {
                    L = mid + 1;
                } else {
                    return new int[]{i + 1, mid + 1};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
