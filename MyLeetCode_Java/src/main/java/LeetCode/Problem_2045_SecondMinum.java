package LeetCode;

import java.util.*;

public class Problem_2045_SecondMinum {

    // https://leetcode-cn.com/problems/second-minimum-time-to-reach-destination/solution/gong-shui-san-xie-yi-ti-shuang-jie-dui-y-88np/
    public int secondMinimum(int n, int[][] edges, int time, int change) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        // 构建邻接表
        // 1: 2,3
        // 2: 4
        // 3: 5
        for (int[] e : edges) {
            if (!map.containsKey(e[0])) {
                map.put(e[0], new ArrayList<>());
            }
            map.get(e[0]).add(e[1]);
        }

        //设置一个n*dist，
        // dist[i][0]表示第一次到达第i个节点的最短时间，
        // dist[i][1]表示第二次到达第i个节点的第二最短时间。
        int[][] dist = new int[n+1][2];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[1][0] = 0; // 0 的最短时间:0 , 次短是Integer.MAX_VALUE
        LinkedList<int []> queue = new LinkedList<>();
        queue.addLast(new int[]{1,0});
        while (!queue.isEmpty()) {
            int[] node = queue.pollFirst();
            int cur = node[0];
            int distance = node[1];
            for (int next : map.get(cur)) {
                if (dist[next][0] > distance + 1) {
                    dist[next][1] = dist[next][0];
                    dist[next][0] = distance + 1;
                    queue.addLast(new int[]{next, dist[next][0]});
                    queue.addLast(new int[]{next, dist[next][1]});
                } else if (dist[next][0] < distance + 1 && dist[next][1] > distance + 1) {
                    dist[next][1] = distance + 1;
                    queue.addLast(new int[]{next, dist[next][1]});
                }
            }
        }
        return pathToTime(dist[n][1], time, change);
    }

    private int pathToTime(int len, int time, int change) {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += time;
            if (i == len - 1) break;
            int period = sum / change;
            if ((period & 1) == 1)
                sum = (period + 1) * change;
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {1, 4}, {3, 4}, {4, 5}};
        int time = 3;
        int change = 5;
        var ans = new Problem_2045_SecondMinum().secondMinimum(n, edges, time, change);
        System.out.println(ans);
    }
}
