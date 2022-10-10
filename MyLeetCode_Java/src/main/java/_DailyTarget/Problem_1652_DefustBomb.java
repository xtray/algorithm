package _DailyTarget;

public class Problem_1652_DefustBomb {

    public int[] decrypt(int[] code, int k) {

        int n = code.length;
        int[] ans = new int[n];
        if (k == 0) return ans;
        int[] sum = new int[n * 2 + 1]; // 抬高1个, 防止0位置判断
        for (int i = 1; i <= 2 * n; i++) {
            sum[i] += sum[i - 1] + code[(i - 1) % n];
        }
        for (int i = 1; i <= n; i++) {
            if (k < 0) { // k为负数, 就是往前-k个
                //  i-|k|-1  ~   i-1
                ans[i - 1] = sum[i + n - 1] - sum[i + n + k - 1];
            } else { // k > 0 i+1 ~ i+k
                ans[i - 1] = sum[i + k] - sum[i];
            }
        }
        return ans;
    }

}
