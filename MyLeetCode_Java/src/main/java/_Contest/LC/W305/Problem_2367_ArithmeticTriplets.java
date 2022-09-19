package _Contest.LC.W305;

public class Problem_2367_ArithmeticTriplets {

    public int arithmeticTriplets(int[] nums, int diff) {
        int N = nums.length;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
