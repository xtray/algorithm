package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem_592_ValidSquare {


    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = new int[4][2];
        points[0][0] = p1[0];
        points[0][1] = p1[1];
        points[1][0] = p2[0];
        points[1][1] = p2[1];
        points[2][0] = p3[0];
        points[2][1] = p3[1];
        points[3][0] = p4[0];
        points[3][1] = p4[1];
        // 枚举任意两个点, 计算距离
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                set.add(getDis(points[i], points[j]));
            }
        }
        return !set.contains(0) && set.size() == 2;
    }

    private int getDis(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
