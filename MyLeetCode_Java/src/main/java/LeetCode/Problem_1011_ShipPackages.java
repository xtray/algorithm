package LeetCode;

public class Problem_1011_ShipPackages {


    // 二分
    public int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        int max = weights[0];
        for (int w : weights) {
            sum += w;
            max = Math.max(max, w);
        }
        int L = max;
        int R = sum;
        int res = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (ok(weights, days, mid)) {
                res = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return res;
    }

    private boolean ok(int[] weights, int days, int cap) {
        int cnt = 0;
        int sum = 0;
        for (int w : weights) {
            sum += w;
            if (sum > cap) {
                cnt++;
                sum = w;
            }
        }
        cnt += sum > 0 ? 1 : 0;
        return cnt <= days;
    }

    public static void main(String[] args) {
        // int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        // int days = 5; // 15
        int[] weights = {1, 2, 3, 1, 1};
        int days = 4;

        var ans = new Problem_1011_ShipPackages().shipWithinDays(weights, days);
        System.out.println(ans);
    }
}
