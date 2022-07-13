package LeetCode;

import java.util.PriorityQueue;

// IMP: 重要二分题
//  ref: Problem_875_KokoEatBanana
// https://leetcode.cn/problems/minimize-max-distance-to-gas-station/

public class Problem_774_MinMaxGasDistance {


    // 大根堆
    public double minmaxGasDist(int[] stations, int K) {
        int N = stations.length;
        // int[] : [间隔长度, 间隔被切分的份数]
        // 一开始份数为1, 表示没有塞入
        // 按每一份的间隔大小从大到小排序, 塞入
        // PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) ->
        //         (double) b[0] / b[1] < (double) a[0] / a[1] ? -1 : 1);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) ->
                Double.compare((double) b[0] / b[1], (double) a[0] / a[1]));
        for (int i = 0; i < N - 1; ++i) {
            pq.add(new int[]{stations[i + 1] - stations[i], 1});
        }
        while (K > 0) {
            int[] node = pq.poll();
            node[1]++; // 塞入一个加油站, 间隔变为  node[0]/node[1]
            pq.add(node);
            K--;
        }
        int[] node = pq.poll(); // 取出间隔最大的
        return (double) node[0] / node[1];
    }


    public double minmaxGasDist1(int[] stations, int K) {
        // 精度
        double accuracy = 0.0000001D;
        double l = 0;
        double r = 100000000D;
        double m = 0;
        double ans = 0;

        // int pre = stations[0];
        // int maxDist = 0;
        // for (int s : stations) {
        //     maxDist = Math.max(maxDist, s - pre);
        //     pre = s;
        // }
        // r = maxDist;

        while (r - l > accuracy) {
            m = (l + r) / 2;
            if (ok(m, stations, K)) {
                r = m;
                ans = m;
            } else {
                l = m;
            }
        }
        return ans;
    }


    // int[] stations : 所有加油站的分布情况！
    // double limit : 强制要求，相邻加油站的距离，不能超过limit
    // int K : 一共可以使用的加油站数量！
    // 所有加油站的分布情况, 相邻加油站的距离, 共可以使用的加油站数量, 能不能做到！
    public static boolean ok(double limit, int[] stations, int K) {
        int used = 0;
        for (int i = 1; i < stations.length; i++) {
            used += (int) ((stations[i] - stations[i - 1]) / limit);
            if (used > K) {
                return false;
            }
        }
        return true;
    }


    // https://leetcode.cn/problems/minimize-max-distance-to-gas-station/solution/bi-guan-fang-da-an-geng-hao-li-jie-yi-xie-da-jia-k/
    public static boolean ok1(double limit, int[] stations, int K) {
        int used = 0;
        for (int i = 1; i < stations.length; i++) {
            double gap = stations[i] - stations[i - 1];
            if(gap - limit > 1e-6){ // 大于1个limit的才需要塞入
                double cnt = gap / limit;
                double modCnt = gap % limit; // IMP: 学习doube取模
                if (modCnt <= 1e-6) {
                    used += (int) cnt - 1;
                } else {
                    used += (int) cnt;
                }
            }
            if (used > K) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        // int[] stations = {10, 19, 25, 27, 56, 63, 70, 87, 96, 97};
        // int k = 3; // 9.66667
        int[] stations = {0, 10, 15};
        int k = 1; //
        var ans = new Problem_774_MinMaxGasDistance().minmaxGasDist1(stations, k);
        System.out.println(ans);


        double gap = 10.0000;
        double limit = 5.0;
        double modCnt = gap % limit;
        System.out.println(modCnt);
    }

}
