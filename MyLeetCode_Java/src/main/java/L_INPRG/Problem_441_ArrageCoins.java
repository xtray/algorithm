package L_INPRG;

public class Problem_441_ArrageCoins {
    public int arrangeCoins(int n) {
        if (n < 0) {
            return 0;
        }
        int L = 1;
        int R = n;
        int ans = 0;
        long ans_sum = 0;
        long sum = 0;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            sum = (long) (1 + mid) * mid / 2;
            if (sum >= n) {
                ans = mid;
                ans_sum = sum;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans_sum == n ? ans : ans -1;
    }

    // IMP: 按小于等于计算, 不用修正
    public int arrangeCoins2(int n) {
        if (n < 0) {
            return 0;
        }
        int L = 1;
        int R = n;
        int ans = 0;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if ((long) (1 + mid) * mid <= 2L * n) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 6;
        var ans = new Problem_441_ArrageCoins().arrangeCoins2(n);
        System.out.println(ans);
    }
}
