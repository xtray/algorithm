package LeetCode;

import java.util.Arrays;

public class Problem_1470_Shuffle {

    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[n << 1];
        for (int i = 0, j = 0; i < n<<1; i += 2, j++) {
            ans[i] = nums[j];
            ans[i + 1] = nums[n + j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 1, 3, 4, 7};
        int n = 3;
        var ans = new Problem_1470_Shuffle().shuffle(nums, n);
        System.out.println(Arrays.toString(ans));
    }

}
