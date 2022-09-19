package LeetCode;

// IMP: 差分板子题!!

public class Problem_1109_CorporateFlightBookings {

    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] ans = new int[n];
        if (bookings == null || bookings.length == 0 || bookings[0] == null || bookings[0].length == 0) {
            return ans;
        }
        int[] diff = new int[n + 1]; // 使用0位置
        for (int[] bo : bookings) {
            int L = bo[0] - 1; // 数据从1开始
            int R = bo[1] - 1;
            int V = bo[2];
            diff[L] += V;
            diff[R + 1] -= V;
        }
        ans[0] = diff[0];
        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1] + diff[i];
        }
        return ans;
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        if (bookings == null || bookings.length == 0 || bookings[0] == null || bookings[0].length == 0) {
            return ans;
        }
        int[] diff = new int[n + 2]; // 下标 0 弃而不用, 1~n+1使用
        for (int[] b : bookings) {
            int s = b[0];
            int e = b[1] + 1;
            int v = b[2];
            diff[s] += v;
            diff[e] -= v;
        }
        // 差分数组处理成累加和数组就是答案
        ans[0] = diff[1];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + diff[i + 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        var ans = new Problem_1109_CorporateFlightBookings().corpFlightBookings(bookings, n);
        for (var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
