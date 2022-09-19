package _LintCode;

import java.util.*;

public class Problem_1144_MaxCount {

    public int maxCount(int m, int n, int[][] ops) {
        int minRow = m;
        int minCol = n;
        for (int[] op : ops) {
            int a = op[0];
            int b = op[1];
            minRow = Math.min(minRow, a);
            minCol = Math.min(minCol, b);
        }

        return minRow * minCol;
    }
}
