package LeetCode.Contest.W290;

import java.util.*;

public class Problem_6042_CountLatticePoints {

    private static final int MAX_BORDER = 201;

    public int countLatticePoints(int[][] circles) {
        boolean[][] mask = new boolean[MAX_BORDER][MAX_BORDER];
        for (int[] c : circles) {
            fillMask(c, mask);
        }
        int ans = 0;
        for (int i = 0; i < MAX_BORDER; i++) {
            for (int j = 0; j < MAX_BORDER; j++) {
                if (mask[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private void fillMask(int[] c, boolean[][] mask) {
        int R = c[2];
        int baseX = c[0];
        int baseYUp = c[1] + R;
        int baseYDown = c[1] - R;
        int cnt = 0;
        while (cnt <= R) {
            int border = (int) (Math.pow(Math.pow(R, 2) - Math.pow(R - cnt, 2), 0.5));
            for (int j = 0; j <= border; j++) {
                mask[baseX + j][baseYUp] = true;
                mask[baseX - j][baseYUp] = true;
                mask[baseX + j][baseYDown] = true;
                mask[baseX - j][baseYDown] = true;
            }
            cnt++;
            baseYUp--;
            baseYDown++;
        }
    }

    public static void main(String[] args) {
        // int[][] circles = new int[][]{{2,2,1}};
        // int[][] circles = new int[][]{{2, 2, 2}, {3, 4, 1}};
        // 141
        int[][] circles = new int[][]{{8,9,6},{9,8,4},{4,1,1},{8,5,1},{7,1,1},{6,7,5},{7,1,1},{7,1,1},{5,5,3}};
        // int[][] circles = new int[][]{{8, 9, 6}}; // 113
        var ans = new Problem_6042_CountLatticePoints().countLatticePoints(circles);
        System.out.println(ans);
    }

}
