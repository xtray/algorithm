package LeetCode;

public class Problem_69_Sqrtx {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x <= 3) {
            return 1;
        }
        int L = 0;
        int R = x / 2;
        int ans = 0;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (mid <= x / mid) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int x = 8;
        var ans = new Problem_69_Sqrtx().mySqrt(x);
        System.out.println(ans);
    }
}
