package _Contest.LCP;

public class Problem_LCP06_MinCount {

    public int minCount(int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int c : coins) {
            ans += (c + 1) / 2; // 向上取整
        }
        return ans;
    }
}
