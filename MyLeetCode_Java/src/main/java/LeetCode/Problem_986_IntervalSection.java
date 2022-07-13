package LeetCode;

import java.util.ArrayList;
import java.util.List;

// IMP: 学习这种双指针的移动方法

public class Problem_986_IntervalSection {

    // https://leetcode.cn/problems/interval-list-intersections/solution/jiu-pa-ni-bu-dong-shuang-zhi-zhen-by-hyj8/

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList == null || firstList.length == 0 || secondList == null || secondList.length == 0) {
            return new int[0][];
        }
        int N = firstList.length;
        int M = secondList.length;
        int i = 0;
        int j = 0;
        List<int[]> list = new ArrayList<>();
        while (i < N && j < M) {
            // 交集区间的 start 取的是 A、B 子区间中较大的左界
            int start = Math.max(firstList[i][0], secondList[j][0]);
            // 交集区间的 end 取的是 A、B 子区间中较小的右界。
            int end = Math.min(firstList[i][1], secondList[j][1]);
            if (start <= end) {
                list.add(new int[]{start, end});
            }
            // 谁先结束，谁的指针就移动到下一个子区间
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
