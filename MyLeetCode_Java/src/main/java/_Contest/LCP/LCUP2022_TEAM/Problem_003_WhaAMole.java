package _Contest.LCP.LCUP2022_TEAM;

import java.util.*;

// https://leetcode-cn.com/problems/ZbAuEH/
// LCP 57. 打地鼠

public class Problem_003_WhaAMole {

    public static class NodeInfo {
        public int x;
        public int y;
        public int time;

        public NodeInfo(int x, int y, int t) {
            this.x = x;
            this.y = y;
            time = t;
        }
    }

    private static int maxTime = 0;

    // 耗子洞数组按时间排序
    //
    // 从Start开始, 收集从起始点出发到其他点的距离
    // 尝试start是否有可能踏上每一个其他点
    // 在每一个登上的其它点,从当前时间点开始计算时间点大>=当前的下一步的可能点BFS

    // TLE
    public int getMaximumNumber(int[][] moles) {
        List<NodeInfo> list = new ArrayList<>();
        for (int[] m : moles) {
            int x = m[1];
            int y = m[2];
            int t = m[0];
            NodeInfo info = new NodeInfo(x, y, t);
            list.add(info);
            maxTime = Math.max(maxTime, t);
        }
        list.sort((o1, o2) -> o1.time - o2.time);
        int ans = process(list, 1, 1, 0);
        return ans;
    }

    // 待访问的点都在list里
    // 当前来到的点是 (x, y) 位置,
    // 当前时间点是 curTime
    // 返回从当前点出发能打的老鼠最大只数
    private int process(List<NodeInfo> list, int x, int y, int curTime) {
        if (curTime > maxTime) {
            return 0;
        }
        // 尝试每一个点作为登录的第一个点
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            NodeInfo first = list.get(i);
            int dis = getManDis(first.x, first.y, x, y);
            int occurTime = first.time;
            if (curTime + dis <= occurTime) { // 可以登录这个节点
                list.remove(i);
                res = Math.max(res, 1 + process(list, first.x, first.y, occurTime));
                list.add(i, first);
            }
        }
        return res;
    }

    private int getManDis(int x, int y, int curX, int curY) {
        return Math.abs(curX - x) + Math.abs(curY - y);
    }

    // ref: https://leetcode-cn.com/problems/ZbAuEH/solution/dong-tai-gui-hua-by-planet87-npcq/


    public static void main(String[] args) {
        int[][] moles = {{1, 1, 0}, {2, 0, 1}, {4, 2, 2}};
        // int[][]moles = {{2,0,2},{5,2,0},{4,1,0},{1,2,1},{3,0,2}};
        var ans = new Problem_003_WhaAMole().getMaximumNumber(moles);
        System.out.println(ans);
    }


}
