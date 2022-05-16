package LeetCode;

public class Problem_944_MinDelSize {

    public int minDeletionSize(String[] strs) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int N = strs.length;
        int M = strs[0].length();
        int ans = 0;
        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                if (strs[i].charAt(j) < strs[i - 1].charAt(j)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }
}
