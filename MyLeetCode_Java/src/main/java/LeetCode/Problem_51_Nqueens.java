package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_51_Nqueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        int[] record = new int[n];
        process(n, 0, record, ans);
        return ans;
    }

    // 当前处理到i行, 之前的方案在record数据里
    private void process(int n, int i, int[] record, List<List<String>> ans) {
        if (i == n) {
            List<String> res = new ArrayList<>();
            for (int queuePos : record) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == queuePos) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                res.add(sb.toString());
            }
            ans.add(res);
            return;
        }

        // 尝试每一个位置
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                process(n, i + 1, record, ans);
            }
        }
    }

    // 检查 i行放在j位置是否合法
    private boolean isValid(int[] record, int i, int j) {
        // 0...i-1
        //  record[k] :  第k行queue的位置
        for (int k = 0; k < i; k++) {
            // IMP: 注意两个斜线约束的写法, 推导如下:
            // k 行 queue位置: record[k]
            // 当前i行, 斜线约束需要以i位置往左右错  record[k] +/- (i-k)
            // j == record[k] +/- (i-k)
            // record[k] - j == +/- (i-k)
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }


}
