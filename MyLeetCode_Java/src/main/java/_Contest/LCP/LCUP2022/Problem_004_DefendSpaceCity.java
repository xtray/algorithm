package _Contest.LCP.LCUP2022;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/EJvmW4/

public class Problem_004_DefendSpaceCity {

    public int defendSpaceCity(int[] time, int[] position) {

        int[] mask = new int[102]; // 长度为101的屏障, 位置为1有屏障
        int N = time.length;
        // 初始化
        // 1,2,3,4,5: 5个时间点
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i <= 5; i++) { // index 0 不用
            list.add(new int[102]);
        }

        for (int i = 0; i < time.length; i++) {
            int curTime = time[i];
            int curPos = position[i];
            list.get(curTime)[curPos] = 1;
        }

        // 从每一个time开始往下遍历
        // list数组存储了每个时刻的被攻击位置
        // for(int t = 1; t<=5; t++) {
        //     // 当前t时刻的攻击情况
        //     int[] attack = list.get(t);
        //     // 从左往右遍历attack数组, 为1的时候做选则
        //     // 1. 开屏障1范围
        //     // 2. 开屏障2范围
        //     // 3. 维持上一步mask中的屏障
        //     // 取最小
        //     process(attack, mask)
        // }
        var ans = process(list, mask, 1, 0);
        return ans;
    }

    // mask: 上次的屏障覆盖范围
    // list: 数组存储了每个时刻的被攻击位置
    // timeIdx: 当前来到的攻击时刻
    // 从timeIdx出发, 保护, 最小的能量
    private int process(List<int[]> list, int[] mask, int timeIdx, int idx) {
        if (timeIdx == 6) {
            return 0;
        }
        // 在当前时刻, level 做DFS
        return dfs(list, mask, timeIdx, idx);
    }

    // 在attack数组, 当前来到i位置, 前次防护mask, 返回当前行的选择
    private int dfs(List<int[]> list, int[] mask, int timeIdx, int i) {
        int[] attack = list.get(timeIdx); // 当前的受攻击数组
        if (i == attack.length) { // 这一行处理完了, 去下一个时刻
            return process(list, mask, timeIdx + 1, 0);
        }
        int p1 = Integer.MAX_VALUE;
        int p2 = Integer.MAX_VALUE;
        int p3 = Integer.MAX_VALUE;
        int ans = 0;
        if (attack[i] == 1) {
            // 1.开启屏障
            int old = mask[i];
            mask[i] = 1;
            p1 = 2 + dfs(list, mask, timeIdx, i + 1);
            mask[i] = old;
            // 2. 开启2屏障
            if (i + 1 < mask.length) {
                mask[i] = 1;
                int old2 = mask[i + 1];
                mask[i + 1] = 1;
                p2 = 3 + dfs(list, mask, timeIdx, i + 2);
                mask[i] = old;
                mask[i + 1] = old2;
            }
            // 3.维持之前的屏障
            if (mask[i] == 1) {
                p3 = 1 + dfs(list, mask, timeIdx, i + 1);
            }
            ans = Math.min(p1, Math.min(p2, p3));
        } else {
            ans = dfs(list, mask, timeIdx, i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] time = {1, 2, 1};
        // int[] position = {6, 3, 3};
        // int[] time = {1, 1, 1, 2, 2, 3, 5};
        // int[] position = {1, 2, 3, 1, 2, 1, 3};

        int[] time = {1, 1, 5, 5};
        int[] position = {1, 2, 2, 3};
        var ans = new Problem_004_DefendSpaceCity().defendSpaceCity(time, position);
        System.out.println(ans);
    }
}
