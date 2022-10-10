package LeetCode;

public class Problem_2419_MaxBitwiseSubArray {

    public int longestSubarray(int[] nums) {
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }
        // 最大值的最大连续长度
        int cnt = 0;
        int maxCnt = 0;
        for (int num : nums) {
            if (num == maxNum) {
                cnt++;
                maxCnt = Math.max(maxCnt, cnt);
            } else {
                cnt = 0;
            }
        }
        return maxCnt;
    }
}
