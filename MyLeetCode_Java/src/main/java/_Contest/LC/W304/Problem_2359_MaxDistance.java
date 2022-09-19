package _Contest.LC.W304;

import java.util.*;

public class Problem_2359_MaxDistance {


    // https://leetcode.cn/problems/find-closest-node-to-given-two-nodes/solution/by-lfool-t4y7/
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int N = edges.length;
        int[] dis1 = getDis(edges, node1);
        int[] dis2 = getDis(edges, node2);
        int minDis = Integer.MAX_VALUE;
        int pos = -1;
        for (int i = 0; i < N; i++) {
            int dis = Math.max(dis1[i], dis2[i]);
            if(dis < minDis) {
                minDis = dis;
                pos = i;
            }
        }
        return pos;
    }

    private int[] getDis(int[] edges, int start) {
        int[] dis = new int[edges.length];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int d = 0;
        while (start != -1 && dis[start] == Integer.MAX_VALUE) {
            dis[start] = d++;
            start = edges[start];
        }
        return dis;
    }

    public static void main(String[] args) {
        int[] edges = {2, 2, 3, -1};
        int node1 = 0, node2 = 1;
        var ans = new Problem_2359_MaxDistance().closestMeetingNode(edges, node1, node2);
        System.out.println(ans);
    }
}
