package _Contest.LC.JK;

public class Problem_JK03_MinOps {

    public int minOperations(int[] numbers) {
        long lcm = 1;
        for (int num : numbers) {
            lcm = lcm(lcm, num);
        }
        long ans = 0;
        for (int num : numbers) {
            long cur = lcm / num;
            long res = getCurFactors(cur);
            if (res == -1) {
                return -1;
            }
            ans += res;
        }
        return (int) ans;
    }

    private long getCurFactors(long cur) {
        long cnt = 0;
        while (cur % 2 == 0) {
            cnt++;
            cur /= 2;
        }
        while (cur % 3 == 0) {
            cnt++;
            cur /= 3;
        }
        if (cur == 1) {
            return cnt;
        }
        return -1;
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static long lcm(long m, long n) {
        return m / gcd(m, n) * n;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{50, 75, 100};
        int[] nums = new int[]{10, 14};
        var ans = new Problem_JK03_MinOps().minOperations(nums);
        System.out.println(ans);
    }

}
