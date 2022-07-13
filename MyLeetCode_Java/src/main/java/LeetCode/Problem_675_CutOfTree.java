package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// IMP: 从一个点走到另一个点怎么走最经济

public class Problem_675_CutOfTree {

    public int cutOffTree(List<List<Integer>> forest) {
        int N = forest.size();
        int M = forest.get(0).size();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int val = forest.get(i).get(j);
                if (val > 1) { // 收集所有要去的树
                    list.add(new int[]{i, j, val});
                }
            }
        }
        list.sort((o1, o2) -> o1[2] - o2[2]); // 按高度排序
        int ans = 0;
        int lastR = 0; // 初始位置(0,0)
        int lastC = 0;
        for (int[] cell : list) {
            int step = bestWalk(forest, lastR, lastC, cell[0], cell[1]);
            if (step == -1) {
                return -1;
            }
            ans += step;
            lastR = cell[0];
            lastC = cell[1];
            forest.get(lastR).set(lastC, 1); // 处理为地面
        }
        return ans;
    }

    private static final int[] dir = {0, -1, 0, 1, 0};
    //                                   左, 上, 右, 下

    // 返回从sR,sC --> tR,tC的最少步数
    private int bestWalk(List<List<Integer>> forest, int sR, int sC, int tR, int tC) {
        int N = forest.size();
        int M = forest.get(0).size();
        boolean[][] seen = new boolean[N][M];
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, sR, sC});
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int step = cur[0];
            int curR = cur[1];
            int curC = cur[2];
            if (curR == tR && curC == tC) {
                return step;
            }
            seen[curR][curC] = true;
            for (int i = 1; i <= 4; i++) {
                int nR = curR + dir[i - 1];
                int nC = curC + dir[i];
                if (nR < 0 || nR >= N || nC < 0 || nC >= M || seen[nR][nC] || forest.get(nR).get(nC) < 1) {
                    // 越界, 或者走过, 或者障碍的情况
                    continue;
                }
                int[] move = new int[]{step + 1, nR, nC};
                // NOTE: 当前点cur离目标t更近的话, 下一步放在头部
                if ((i == 1 && curC > tC) || (i == 2 && curR > tR) || (i == 3 && curC < tC) || (i == 4 && curR < tR)) {
                    deque.addFirst(move);
                } else {
                    // 更远的话, 放在尾部
                    deque.addLast(move);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] data = {{1, 2, 3}, {0, 0, 4}, {7, 6, 5}};
        List<List<Integer>> forest = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(data[i][0]);
            list.add(data[i][1]);
            list.add(data[i][2]);
            forest.add(list);
        }
        var ans = new Problem_675_CutOfTree().cutOffTree(forest);
        System.out.println(ans);
    }
}
