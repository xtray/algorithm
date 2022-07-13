package Contest.LC.W293;

// https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero/


public class Problem_2275_LargestCombine {

    public int largestCombination(int[] candidates) {
        int[] cnt = new int[32];
        int ans = 0;
        for (int num : candidates) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    cnt[i]++;
                }
                ans = Math.max(ans, cnt[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] candidates = {16,17,71,62,12,24,14};
        int[] candidates = {8, 8};
        var ans = new Problem_2275_LargestCombine().largestCombination(candidates);
        System.out.println(ans);
    }
}
