package _aTemplate;


// Range GCD Query
public class RGQ_01 {

    public static class RGQ {
        public int[][] gcd;

        public RGQ(int[] arr) {
            int n = arr.length;
            int k = power2(n);
            gcd = new int[n + 1][k + 1]; // 下标从1开始
            for (int i = 1; i <= n; i++) {
                gcd[i][0] = arr[i - 1];
            }
            for (int j = 1; (1 << j) <= n; j++) {
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    gcd[i][j] = gcd(
                            gcd[i][j - 1],
                            gcd[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        public static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public int rangeGCD(int l, int r) {
            // l...r -> r - l + 1 -> 2的哪个次方最接近它！
            int k = power2(r - l + 1);
            return gcd(gcd[l][k], gcd[r - (1 << k) + 1][k]);
        }

        // 离n最近的2的某次幂
        private int power2(int n) {
            int ans = 0;
            // m >> 1 : m先减半为了退出循环时 ans 不用--
            while ((1 << ans) <= (n >> 1)) {
                ans++;
            }
            return ans;
        }
    }


    // 为了测试
    public static void main(String[] args) {
        // int[] nums = {9, 6, 9, 3, 15};
        int[] nums = {8, 2, 6, 10};
        int N = nums.length;
        RGQ rgcd = new RGQ(nums);
        System.out.println(rgcd.rangeGCD(1, N)); // 下标从1开始

        System.out.println("Finish.");
    }

}
