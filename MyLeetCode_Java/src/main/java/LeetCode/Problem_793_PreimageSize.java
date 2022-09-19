package LeetCode;


public class Problem_793_PreimageSize {

    public int preimageSizeFZF(int k) {
        long x1 = getMostRightNum(k);
        // k == 0, x2 = -1, 正好对了
        long x2 = getMostRightNum(k - 1);
        return (int) (x1 - x2);
    }

    // 找到<=k个零的最右侧的x
    // NOTE: k可以等于0, 此时R为0, 会有问题, 所以调大了
    private long getMostRightNum(int k) {
        long L = 0;
        long R = 5L * (k+1);

        long ans = -1;
        while (L <= R) {
            long mid = L + ((R - L) >> 1);
            if (getZero(mid) <= k) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ans;
    }

    // 返回一个数阶乘后0的个数
    private static long getZero(long num) {
        if (num <= 1) {
            return 0;
        }
        long cnt = 0;
        while (num > 0) {
            num /= 5;
            cnt += num;
        }
        return cnt;
    }

    public int preimageSizeFZF1(int k) {
        long x1 = getMostLeftNum(k+1);
        long x2 = getMostLeftNum(k);
        return (int) (x1 - x2);
    }

    // 找到>=k个零的最左侧的x
    private long getMostLeftNum(int k) {
        long L = 0;
        long R = 5L * k;

        long ans = -1;
        while (L <= R) {
            long mid = L + ((R - L) >> 1);
            if (getZero(mid) >= k) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int k = (int) 1e9;
        int k = 0;
        var ans = new Problem_793_PreimageSize().preimageSizeFZF(k);
        System.out.println(ans);
    }
}
