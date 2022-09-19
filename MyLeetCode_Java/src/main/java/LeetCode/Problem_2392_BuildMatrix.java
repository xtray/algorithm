package LeetCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_2392_BuildMatrix {

    public int[][] buildMatrix(int k, int[][] rcon, int[][] ccon) {

        // 分别在行, 列上拓扑排序
        List<List<Integer>> rowGraph = new ArrayList<>();
        List<List<Integer>> colGraph = new ArrayList<>();
        int[] rowInMap = new int[k + 1];
        int[] colInMap = new int[k + 1];

        // 初始化
        for (int i = 0; i <= k; i++) {
            rowGraph.add(new ArrayList<>());
            colGraph.add(new ArrayList<>());
        }

        for (int[] r : rcon) {
            rowGraph.get(r[0]).add(r[1]);
            rowInMap[r[1]]++;
        }
        for (int[] c : ccon) {
            colGraph.get(c[0]).add(c[1]);
            colInMap[c[1]]++;
        }

        ArrayDeque<Integer> rowQueue = new ArrayDeque<>();
        for (int i = 1; i <= k; i++) {
            if (rowInMap[i] == 0) {
                rowQueue.addLast(i);
            }
        }
        ArrayDeque<Integer> colQueue = new ArrayDeque<>();
        for (int i = 1; i <= k; i++) {
            if (colInMap[i] == 0) {
                colQueue.addLast(i);
            }
        }

        if (rowQueue.isEmpty() || colQueue.isEmpty()) {
            return new int[0][0];
        }


        int[] rowPos = new int[k + 1];
        int rowSize = 0;
        while (!rowQueue.isEmpty()) {
            int cur = rowQueue.pollFirst();
            rowPos[cur] = rowSize++;
            for (int next : rowGraph.get(cur)) {
                --rowInMap[next];
                if (rowInMap[next] == 0) {
                    rowQueue.addLast(next);
                }
            }
        }

        int[] colPos = new int[k + 1];
        int colSize = 0;
        while (!colQueue.isEmpty()) {
            int cur = colQueue.pollFirst();
            colPos[cur] = colSize++;
            for (int next : colGraph.get(cur)) {
                --colInMap[next];
                if (colInMap[next] == 0) {
                    colQueue.addLast(next);
                }
            }
        }
        if (rowSize != k || colSize != k) {
            return new int[0][0];
        }

        int[][] ans = new int[k][k];
        for (int i = 1; i <= k; i++) {
            int x = rowPos[i];
            int y = colPos[i];
            ans[x][y] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        int k = 3;
        int[][] rcon = {{1, 2}, {3, 2}};
        int[][] ccon = {{2, 1}, {3, 2}};
        var ans = new Problem_2392_BuildMatrix().buildMatrix(k, rcon, ccon);
        System.out.println(Arrays.deepToString(ans));
    }
}
