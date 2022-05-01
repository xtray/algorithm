package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

// IMP: 快速选择算法, 重点题!!

public class Problem_973_KClosestPoint {


    // IMP: 快速选择算法, 重点!!
    public int[][] kClosest(int[][] points, int k) {
        if (points == null || points.length < k) {
            return new int[][]{};
        }
        // 第k小就是找index的第k-1小
        process(points, 0, points.length - 1, k - 1);
        return Arrays.copyOfRange(points, 0, k);
    }

    // 找第 k 小
    // 从[L...R] 范围上, 如果排序的话, 找第 index 的数
    // index 一定在 L..R 上
    private void process(int[][] points, int L, int R, int index) {
        if (L == R) {
            return;
        }
        // 不止一个数  L +  [0, R -L]
        int pos = L + (int) (Math.random() * (R - L + 1));
        int pivot = points[pos][0] * points[pos][0] + points[pos][1] * points[pos][1];
        int[] range = partition(points, L, R, pivot);
        if (index < range[0]) {
            process(points, L, range[0] - 1, index);
        } else if (index > range[1]) {
            process(points, range[1] + 1, R, index);
        }
    }

    private int[] partition(int[][] points, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l; // 盯着的位置
        while (cur < more) {
            int curDist = points[cur][0] * points[cur][0] + points[cur][1] * points[cur][1];
            if (curDist < pivot) {
                swap(points, ++less, cur++);
            } else if (curDist > pivot) {
                swap(points, --more, cur);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    // 小根堆的做法
    public int[][] kClosest2(int[][] points, int k) {
        if (points == null || points.length < k) {
            return new int[][]{};
        }
        // int[x, y] : x^2 + y^2
        // 薄弱的, 最大的放上面, 大根堆
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
                (o2[0] * o2[0] + o2[1] * o2[1]) - (o1[0] * o1[0] + o1[1] * o1[1]));
        for (int[] p : points) {
            if (pq.size() < k) {
                pq.add(new int[]{p[0], p[1]});
            } else { // size == k
                int curDis = p[0] * p[0] + p[1] * p[1];
                int[] top = pq.peek();
                int topDis = top[0] * top[0] + top[1] * top[1];
                if (curDis < topDis) {
                    pq.poll();
                    pq.add(new int[]{p[0], p[1]});
                }
            }
        }
        int[][] ans = new int[k][2];
        int idx = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            ans[idx][0] = cur[0];
            ans[idx++][1] = cur[1];
        }
        return ans;
    }

    public static void main(String[] args) {
        Problem_973_KClosestPoint sl = new Problem_973_KClosestPoint();
        // int[][] points = new int[][]{{-6,-8},{4,-2},{4,5},{5,7},{3,1}};
        int[][] points = new int[][]{{6, 10}, {-3, 3}, {-2, 5}, {0, 2}};
        int k = 3;
        int[][] ans = sl.kClosest(points, k);
        for (int[] num : ans) {
            System.out.printf("(%2d, %2d)\n", num[0], num[1]);
        }
        int[][] ans2 = sl.kClosest2(points, k);
        for (int[] num : ans2) {
            System.out.printf("(%2d, %2d)\n", num[0], num[1]);
        }
    }

}