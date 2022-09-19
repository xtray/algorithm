package LeetCode;

public class Problem_1413_MinStartValue {

    public int minStartValue(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int ans = 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if(sum < 0) {
                ans = Math.max(ans, -sum + 1);
            }
        }
        return ans;
    }
}
