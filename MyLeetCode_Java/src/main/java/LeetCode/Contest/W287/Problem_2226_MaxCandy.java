package LeetCode.Contest.W287;

// W287
// https://leetcode-cn.com/problems/maximum-candies-allocated-to-k-children/

// IMP: 挺典型的二分, 回头多看看
public class Problem_2226_MaxCandy {

    // 二分
    // 每个人最少 1
    // 每个人最多  sum/k
    public int maximumCandies(int[] candies, long k) {
        if(candies == null || candies.length ==0) {
            return 0;
        }
        long sum = 0;
        for(int num : candies) {
            sum += num;
        }
        long L = 1;
        long R = sum/k;
        long maxVal = 0;
        while (L <= R) {
            long mid = L + ((R-L)>>1);
            long cnt = getPileCnt(candies, mid);
            if( cnt >= k) {
                maxVal = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return (int)maxVal;
    }

    private long getPileCnt(int[] candies, long mid) {
        long cnt = 0;
        for(int can : candies) {
            cnt += can/mid;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] candies = {5,8,6};
        long k = 3;
        var ans = new Problem_2226_MaxCandy().maximumCandies(candies, k);
        System.out.println(ans);
    }
}
