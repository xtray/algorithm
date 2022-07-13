package LeetCode;

public class Problem_52_TotalNqueens {

    public int totalNQueens(int n) {
        int[] record = new int[n];
        return process(n, 0, record);
    }

    // 当前处理到i行, 之前的方案在record数据里
    // 返回从i位置到结束的方法数
    private int process(int n, int i, int[] record) {
        if (i == n) {
            return 1;
        }

        int ans = 0;
        // 尝试每一个列位置
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                ans += process(n, i + 1, record);
            }
        }
        return ans;
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

    // 位运算的方法
    // 不要超过32皇后问题
    public static int totalNQueens2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0
        // NOTE: 注意 32个1都满是 - 1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    // 7皇后问题
    // limit : 0....0 1 1 1 1 1 1 1
    // 之前皇后的列影响：colLim
    // 之前皇后的左下对角线影响：leftDiaLim
    // 之前皇后的右下对角线影响：rightDiaLim
    public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1); // 取出最右侧的1
            pos = pos - mostRightOne; // 剩下的位置
            res += process2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }
        return res;
    }


}
