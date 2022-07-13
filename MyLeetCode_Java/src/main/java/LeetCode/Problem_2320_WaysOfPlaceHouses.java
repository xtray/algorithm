package LeetCode;

public class Problem_2320_WaysOfPlaceHouses {
    private static final int mod = (int) 1e9 + 7;

    public int countHousePlacements(int n) {
        long ways = process(n, 0);
        long ans = (ways * ways) % mod;
        return (int) ans;
    }

    // 当前摆放到index = i位置, 返回从i...n-1的方法数
    private long process(int n, int i) {
        if (i >= n) {
            return 1;
        }
        // i位置不放
        long p1 = process(n, i + 1);
        // i位置放
        long p2 = process(n, i + 2);
        return (p1 + p2) % mod;
    }

    public int countHousePlacements2(int n) {
        long dp1 = 1;
        long dp2 = 1;
        for (int i = n - 1; i >= 0; i--) {
            long cur = (dp1 + dp2) % mod;
            dp2 = dp1;
            dp1 = cur;
        }
        long ans = (dp1 * dp1) % mod;
        return (int) ans;
    }


    public static void main(String[] args) {
        int n = 1; // 4
        // int n = 2; // 4
        var ans = new Problem_2320_WaysOfPlaceHouses().countHousePlacements2(n);
        System.out.println(ans);
    }
}
