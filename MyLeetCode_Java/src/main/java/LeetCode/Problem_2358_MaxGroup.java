package LeetCode;

public class Problem_2358_MaxGroup {


    // 1, 2, 3
    // 1...k
    // (1+k)k/2 <= n
    // x^2 + x -2n = 0;
    // a=1, b=1, c= -2N
    public int maximumGroups(int[] grades) {
        int N = grades.length;
        return (int) (-1 + Math.sqrt(1 + 8 * N)) / 2;
    }

    // 二分
    public int maximumGroups1(int[] grades) {
        int N = grades.length;
        int L = 1;
        int R = N;
        long sum = 0;
        int ans = 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            sum = (long) (1 + mid) * mid / 2;
            if (sum > N) {
                R = mid - 1;
            } else {
                ans = mid;
                L = mid + 1;
            }
        }
        return ans;
    }


}
