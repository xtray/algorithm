package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem_2133_CheckNumbers {

    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowSet.add(matrix[i][j]);
                colSet.add(matrix[j][i]);
            }
            if (rowSet.size() != n) return false;
            if (colSet.size() != n) return false;
            rowSet.clear();
            colSet.clear();
        }
        return true;
    }

    public boolean checkValid1(int[][] matrix) {
        int n = matrix.length;
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rowSet.contains(matrix[i][j])) return false;
                rowSet.add(matrix[i][j]);
                if (colSet.contains(matrix[j][i])) return false;
                colSet.add(matrix[j][i]);
            }
            rowSet.clear();
            colSet.clear();
        }
        return true;
    }

    public boolean checkValid2(int[][] matrix) {
        int n = matrix.length;
        boolean[] row = new boolean[101];
        boolean[] col = new boolean[101];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (row[matrix[i][j]]) return false;
                row[matrix[i][j]] = true;
                if (col[matrix[j][i]]) return false;
                col[matrix[j][i]] = true;
            }
            Arrays.fill(row, false);
            Arrays.fill(col, false);
        }
        return true;
    }
}
