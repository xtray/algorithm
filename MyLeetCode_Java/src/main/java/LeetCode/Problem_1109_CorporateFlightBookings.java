package LeetCode;

public class Problem_1109_CorporateFlightBookings {

    // 差分
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        if (bookings == null || bookings.length == 0 || bookings[0] == null || bookings[0].length == 0) {
            return ans;
        }
        int[] diff = new int[n + 2]; // 下标 0 弃而不用
        for (int[] bo : bookings) {
            int L = bo[0];
            int R = bo[1];
            int V = bo[2];
            diff[L] += V;
            diff[R + 1] -= V;
        }
        ans[0] = diff[1];
        for (int i = 1; i < n; i++) {
            ans[i] += ans[i - 1] + diff[i + 1];
        }
        return ans;
    }


    public int[] corpFlightBookings2(int[][] bookings, int n) {
        int[] ans = new int[n];
        if (bookings == null || bookings.length == 0 || bookings[0] == null || bookings[0].length == 0) {
            return ans;
        }
        int[] diff = new int[n + 1];
        for (int[] bo : bookings) {
            int L = bo[0] - 1;
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
