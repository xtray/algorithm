package BigComQues.MS;

/**
 * Given a N by M matrix, find the maximum sum possible if we have to choose
 * one element from each row. For example if matrix has N rows then this
 * sum will be formed by N elements from each row. The only constraint
 * is if we have picked an element from the ith row, then we cannot
 * choose element from the same column in (i-1)th and (i+1)th row [if exist].
 *
 * Example :
 * 1,2,3
 * 4,5,6
 * 7,8,9
 *
 * Maxium sum is : 3 + 5 + 9
 * matrix elements can be random, not sorted as shown in example.
 */


// N*M的数值矩阵, 每一行选取一个数, 求N行能得到的累加和最大值.
// 限制: 当选定的第i行元素不能与相邻的上下两行共列
// 样例:
// 1,2,3
// 4,5,6
// 7,8,9
// 最大值: 3 + 5 + 9 = 17
public class Problem_MS_MaxSum {

    public int getMaxSum(int[][] grid) {
        return process(grid, 0, -1);
    }

    // 当前处理到index行, 前一个选择的数字是preIdx下标
    // 返回从index到结束的最大和值
    private int process(int[][] grid, int index, int preIdx) {
        int n = grid.length;
        int m = grid[0].length;
        if (index == n) {
            return 0;
        }
        // 没到最后一行
        // 来到index行, 选择每一个不是preIdx的数
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (i != preIdx) {
                int cur = grid[index][i] + process(grid, index + 1, i);
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }

    public int getMaxSum1(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Integer[][] dp = new Integer[n + 1][m];
        return process1(grid, 0, -1, dp);
    }

    // 当前处理到index行, 前一个选择的数字是preIdx下标
    // 返回从index到结束的最大和值
    private int process1(int[][] grid, int index, int preIdx, Integer[][] dp) {
        int n = grid.length;
        int m = grid[0].length;
        if (index == n) {
            return 0;
        }
        if (preIdx != -1 && dp[index][preIdx] != null) {
            return dp[index][preIdx];
        }
        // 没到最后一行
        // 来到index行, 选择每一个不是preIdx的数
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (i != preIdx) {
                int cur = grid[index][i] + process1(grid, index + 1, i, dp);
                ans = Math.max(ans, cur);
            }
        }
        if (preIdx != -1) dp[index][preIdx] = ans;
        return ans;
    }

    // 维持第一大, 第二大即可
    // 类似: https://leetcode.cn/problems/paint-house/
    // O(N*M) time, O(1) space
    public int maxSum(int[][] arr) {
        if (arr == null || arr.length == 0 ||
                arr[0] == null || arr[0].length == 0) {
            return 0;
        }
        int N = arr.length;
        int M = arr[0].length; // column
        int preMax1 = 0; // first maximum
        int preMax2 = 0; // second maximum
        int preCol1 = -1; // column of first max
        int preCol2 = -1; // column of second max
        for (int i = 0; i < N; i++) {
            int curMax1 = Integer.MIN_VALUE;
            int curCol1 = -1;
            int curMax2 = Integer.MIN_VALUE;
            int curCol2 = -1;
            // in turn i, try to use the pre max1 to update curmax1, curmax2
            for (int j = 0; j < M; j++) {
                if (j != preCol1) {
                    // 尝试用上次第一小去更新最小值
                    if (preMax1 + arr[i][j] > curMax1) {
                        curMax2 = curMax1;
                        curCol2 = curCol1;
                        curMax1 = preMax1 + arr[i][j];
                        curCol1 = j;
                    } else if (preMax1 + arr[i][j] > curMax2) {
                        curMax2 = preMax1 + arr[i][j];
                        curCol2 = j;
                    }
                } else if (j != preCol2) {
                    // try to use the pre max2, to update curmax1, curmax2
                    if (preMax2 + arr[i][j] > curMax1) {
                        curMax2 = curMax1;
                        curCol2 = curCol1;
                        curMax1 = preMax2 + arr[i][j];
                        curCol1 = j;
                    } else if (preMax2 + arr[i][j] > curMax2) {
                        curMax2 = preMax2 + arr[i][j];
                        curCol2 = j;
                    }
                }
            }
            preMax1 = curMax1;
            preCol1 = curCol1;
            preMax2 = curMax2;
            preCol2 = curCol2;
        }
        return preMax1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 2, -3},
                {4, 5, 6},
                {7, 8, 9}
        }; // 17
        var ans = new Problem_MS_MaxSum().getMaxSum(grid);
        System.out.println(ans);

        var ans1 = new Problem_MS_MaxSum().getMaxSum1(grid);
        System.out.println(ans1);

        var ans2 = new Problem_MS_MaxSum().maxSum(grid);
        System.out.println(ans2);
    }

}
