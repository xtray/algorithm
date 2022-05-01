package LeetCode.Contest.LCUP2022_TEAM;

// package LeetCode.Contest.LCUP2022_TEAM;
// LCP 56. 信物传送
// https://leetcode-cn.com/problems/6UEx57/


public class Problem_002_ConveyOrBelt {

    // // 从start位置开始BFS, 每个位置都尝试试法
    public int conveyorBelt(String[] matrix, int[] start, int[] end) {
        return process(matrix, start[0], start[1], end[0], end[1]);

    }

    // 返回从(x1,y1)到 (x2, y2)位置的最少魔法次数
    private int process(String[] matrix, int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            return 0;
        }
        // 可能性
        return 0;
    }
}
