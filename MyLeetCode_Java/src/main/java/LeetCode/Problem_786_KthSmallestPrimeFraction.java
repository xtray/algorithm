package LeetCode;

import java.util.PriorityQueue;

public class Problem_786_KthSmallestPrimeFraction {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int N = arr.length;
        // 第 k 小, 用大根堆
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0]*1.0/b[1], a[0]*1.0/a[1]));
        for(int i = 0;i<N;i++) {
            for(int j = i+1; j<N;j++) {
                double cur = arr[i]*1.0/arr[j];
                if(pq.size()<k ) {
                    pq.add(new int[] {arr[i], arr[j]});

                } else { // ==k
                    if (pq.peek()[0]*1.0/pq.peek()[1] > cur ) {
                        pq.poll();
                        pq.add(new int[] {arr[i], arr[j]});
                    }
                }
            }
        }
        return pq.peek();
    }

    // 二分的办法需要再次看一下
    // https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/solution/di-k-ge-zui-xiao-de-su-shu-fen-shu-by-le-argw/
    // https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/solution/er-fen-cha-zhao-dp-fu-za-du-ya-suo-by-rocky-23/
    public int[] kthSmallestPrimeFraction1(int[] arr, int k) {
        int N = arr.length;
        double L = 0.0, R = 1.0;
        while (true) {
            double mid = (L + R) / 2;
            int i = -1, count = 0;
            // 记录最大的分数
            int x = 0, y = 1; // x/y

            for (int j = 1; j < N; ++j) { // 枚举每一个分母
                while ((double) arr[i + 1] / arr[j] < mid) { // 枚举该分母下的每一个分子
                    ++i;
                    if (arr[i] * y > arr[j] * x) {
                        x = arr[i];
                        y = arr[j];
                    }
                }
                count += i + 1;
            }

            if (count == k) {
                return new int[]{x, y};
            }
            if (count < k) {
                L = mid;
            } else {
                R = mid;
            }
        }
    }

}
