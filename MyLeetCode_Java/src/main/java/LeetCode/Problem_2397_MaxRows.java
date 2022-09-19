package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_2397_MaxRows {

    public int maximumRows0(int[][] mat, int cols) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowSum = new int[m];
        int[] colSum = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowSum[i]++;
                    colSum[j]++;
                }
            }
        }
        List<int[]> colList = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            colList.add(new int[]{j, colSum[j]});
        }
        // 每列1的多少按大小排序, 优先选1多的
        colList.sort((o1, o2) -> o2[1] - o1[1]);
        // [大小, 选取的列list]
        Map<Integer, List<Integer>> selectMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int size = colList.get(i)[1];
            int col = colList.get(i)[0];
            if (selectMap.containsKey(size)) {
                selectMap.get(size).add(col);
            } else {
                if (selectMap.size() == cols) break;
                selectMap.computeIfAbsent(size, k -> new ArrayList<>()).add(col);
            }
        }
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            int rowCnt = rowSum[i];
            if (rowCnt == 0) {
                cnt++;
                continue;
            }
            // 尝试map中的每一个列的选择
            for (int j = 0; j < selectMap.size(); j++) {

                for (int col : selectMap.get(j)) {
                    rowCnt -= mat[i][col];
                }


            }
            if (rowCnt == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length;
        int n = mat[0].length;
        int[] rowSum = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    rowSum[i]++;
                }
            }
        }
        List<Integer> selectList = getAllCols(n, cols);
        int maxCnt = 0;
        for (int col : selectList) {
            int curCnt = 0;
            for (int i = 0; i < m; i++) {
                int rowCnt = rowSum[i];
                // 取出这个col里所有的1的列
                for (int j = 0; j < n; j++) {
                    if ((col >> j & 1) == 1) {
                        // 选的第j列
                        // 枚举行
                        rowCnt -= mat[i][j];
                    }
                }
                if (rowCnt == 0) {
                    curCnt++;
                }
            }
            maxCnt = Math.max(maxCnt, curCnt);
        }
        return maxCnt;
    }

    private List<Integer> getAllCols(int n, int cols) {
        List<Integer> list = new ArrayList<>();
        process(n, cols, 0, 0, list);
        return list;
    }

    private void process(int n, int cols, int index, int status, List<Integer> list) {
        if (Integer.bitCount(status) == cols) {
            list.add(status);
            return;
        }
        if (index == n) return;
        process(n, cols, index + 1, status, list);
        process(n, cols, index + 1, status | (1 << index), list);
    }

    /**
     * [[1,0,1,1,1,1],[0,0,0,1,1,0],[1,1,0,0,0,0],[0,0,1,1,0,1]]
     * 2
     * -->1
     */

    public static void main(String[] args) {
        int[][] mat = {
                {1, 0, 1, 1, 1, 1},
                {0, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 1}
        };
        int cols = 2;
        var ans = new Problem_2397_MaxRows().maximumRows(mat, cols);
        System.out.println(ans);
    }
}
