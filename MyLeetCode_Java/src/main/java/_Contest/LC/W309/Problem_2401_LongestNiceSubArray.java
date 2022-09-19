package _Contest.LC.W309;

public class Problem_2401_LongestNiceSubArray {

    public int longestNiceSubarray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int N = nums.length;
        int L = 0;
        int R = 1;
        int or = nums[0];
        int maxCnt = 0;
        while (R < N) {
            // L...R 或 和
            if (L < R && (or & nums[R]) != 0) {
                L++;
                or = nums[L];
            } else {
                or |= nums[R];
                maxCnt = Math.max(maxCnt, R - L + 1);
                R++;
            }
        }
        return maxCnt;
    }


    public static void main(String[] args) {
        // System.out.println(Integer.toBinaryString(48));
        int[] nums = {1, 3, 8, 48, 10};
        var ans = new Problem_2401_LongestNiceSubArray().longestNiceSubarray(nums);
        System.out.println(ans);
    }
}
