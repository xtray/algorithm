package _Misc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://www.luogu.com.cn/problem/P1002

public class NumOfPath {

    public int countPath(int[] input) {

        // 定义 dp
        // dp[i][j]: i,j 位置到最右下角的方法数
        int N = input[0] + 1;
        int M = input[1] + 1;
        int[][] dp = new int[N][M];
        Set<Integer> set = getAllBlockPoints(input);
        int index = (N-1)*M + M - 1;
        dp[N-1][M-1] = set.contains(index)?0:1;
        // 填倒数第1行
        for (int j = M - 2; j>=0; j--) {
            index = (N-1)*M + j;
            boolean flag = set.contains(index);
            dp[N-1][j] = flag? 0:dp[N-1][j+1];
        }
        // 填倒数第1列
        for (int i = N - 2; i>=0; i--) {
            index = i*M + M-1;
            dp[i][M-1] = set.contains(index)? 0:dp[i+1][M-1];
        }
        for (int j = M - 2; j>=0; j--) {
            for (int i = N - 2; i>=0; i--) {
                index = i*M + j;
                if (set.contains(index)) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }
            }
        }

        return dp[0][0];
    }

    public static class Point {
        private int x;
        private int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 根据马点计算出所有 block 点
    private Set<Integer> getAllBlockPoints(int[] input) {
        // B 点 input[0], input[1]
        // 马 点 input[2], input[3]

        int N = input[0] + 1;
        int M = input[1] + 1;
        int x = input[2];
        int y = input[3];
        List<Point> list = new ArrayList<>();
        list.add(new Point(x,y));
        list.add(new Point(x-2,y-1));
        list.add(new Point(x-2,y+1));
        list.add(new Point(x-1,y+2));
        list.add(new Point(x+1,y+2));
        list.add(new Point(x+2,y+1));
        list.add(new Point(x+2,y-1));
        list.add(new Point(x+1,y-2));
        list.add(new Point(x-1,y-2));
        Set<Integer> ans = new HashSet<>();
        for (Point p: list) {
            if (p.x >=0 && p.x <N && p.y >=0 && p.y <M) {
                // 二维坐标转换为一维坐标
                int idx = p.x * M + p.y;
                ans.add(idx);
            }
        }
        return ans;
    }



    public static void main(String[] args) {
//        int[] input = new int[]{6, 6, 3, 3};
        int[] input = new int[]{8, 6, 0, 4};
        NumOfPath sl = new NumOfPath();
        int ans = sl.countPath(input);
        System.out.println(ans);
    }
}
