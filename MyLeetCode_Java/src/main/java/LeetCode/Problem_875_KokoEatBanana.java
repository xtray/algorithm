package LeetCode;

// IMP: 重要二分题
//  ref: Problem_774_MinMaxGasDistance

public class Problem_875_KokoEatBanana {

    public int minEatingSpeed(int[] piles, int h) {
        int L = 1; // NOTE: 重要, 区间是1~max, L = 0会出错
        int R = 0;
        for (int p : piles) {
            R = Math.max(R, p);
        }
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (eatTime(piles, mid) <= h) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    // 以k的速度吃完香蕉的时间
    private long eatTime(int[] piles, int k) {
        long ans = 0;
        for (int p : piles) {
            ans += (p + k - 1) / k; // 向上取整
        }
        return ans;
    }

    public int minEatingSpeed1(int[] piles, int H) {
        int maxVal = 1;
        for (int pile : piles) {
            maxVal = Math.max(maxVal, pile);
        }

        // 速度最小的时候，耗时最长
        int left = 1;
        // 速度最大的时候，耗时最短
        int right = maxVal;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (calculateSum(piles, mid) > H) {
                // 耗时太多，说明速度太慢了，下一轮搜索区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 如果返回的小时数严格大于 H，就不符合题意
     *
     * @param piles
     * @param speed
     * @return 需要的小时数
     */
    private int calculateSum(int[] piles, int speed) {
        int sum = 0;
        for (int pile : piles) {
            // 上取整可以这样写
            sum += (pile + speed - 1) / speed;
        }
        return sum;
    }

    public static void main(String[] args) {
        // int[] piles = {3, 6, 7, 11};
        // int h = 8; // 8
        // int[] piles = {312884470};
        // int h = 968709470; // 1
        int[] piles = {1000000000, 1000000000, 1000000000};
        int h = 1000000000; // 3
        var ans = new Problem_875_KokoEatBanana().minEatingSpeed1(piles, h);
        System.out.println(ans);
    }


}
