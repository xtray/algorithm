package _Contest.LC.W308;

import java.util.Arrays;

public class Problem_2389_LongestSubsequence {

    public int[] answerQueries(int[] nums, int[] queries) {
        int N = nums.length;
        int M = queries.length;
        int[] ans = new int[M];
        Arrays.sort(nums);
        int[] sum = new int[N];
        int pre = 0;
        for (int i = 0; i < N; i++) {
            sum[i] += pre + nums[i];
            pre = sum[i];
        }
        for (int i = 0; i < M; i++) {
            int cnt = getLessEqual(sum, queries[i]);
            ans[i] = cnt;
        }
        return ans;
    }

    private int getLessEqual(int[] sum, int k) {
        int L = 0;
        int R = sum.length - 1;
        int cnt = -1;
        // 找到<=k最右位置
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (sum[mid] <= k) {
                cnt = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return cnt == -1 ? 0 : cnt + 1;
    }
}
