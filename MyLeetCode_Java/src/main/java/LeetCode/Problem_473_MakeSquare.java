package LeetCode;

import java.util.Arrays;

public class Problem_473_MakeSquare {

    public boolean makesquare(int[] ms) {
        if(ms == null || ms.length ==0) {
            return false;
        }
        int sum = 0;
        for(int m : ms) {
            sum += m;
        }
        int edge = sum % 4;
        if(sum % 4 != 0) {
            return false;
        }
        Arrays.sort(ms);
        int N = ms.length;
        int L = 0;
        int R = ms.length - 1;
        // NOTE: 从大到小排序, 学习list 从大到小排序
        while (L < R) {
            int tmp = ms[L];
            ms[L++] = ms[R];
            ms[R++] = tmp;
        }
        int[] edges = new int[4];
        return dfs(0, ms, edges, sum / 4);
    }

    public boolean dfs(int index, int[] matchsticks, int[] edges, int len) {
        if (index == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= len && dfs(index + 1, matchsticks, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[index];
        }
        return false;
    }

}
