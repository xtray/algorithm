package LeetCode;

public class Problem_2341_NumOfPairs {

    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        int[] ans = new int[2];
        for (int num : cnt) {
            if (num == 0) continue;
            ans[0] += num / 2;
            ans[1] += num % 2;
        }
        return ans;
    }
}
