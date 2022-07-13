package LeetCode;

import java.util.TreeMap;

public class Problem_2055_PlatesBetweenCandles {


    // 前缀和放的是 盘子 *
    // 有序表放的是 柱子 |
    public int[] platesBetweenCandles(String s, int[][] queries) {

        char[] str = s.toCharArray();
        int[] sum = new int[str.length];

        sum[0] = str[0] == '*' ? 1 : 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        if (str[0] == '|') {
            map.put(0, 1);
        }

        for (int i = 1; i < str.length; i++) {
            sum[i] += sum[i - 1] + (str[i] == '*' ? 1 : 0);
            if (str[i] == '|') {
                map.put(i, 1);
            }
        }

        int[] ans = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            int low = q[0];
            int up = q[1];
            int L = map.ceilingKey(low) != null ? map.ceilingKey(low) : str.length;
            int R = map.floorKey(up) != null ? map.floorKey(up) : -1;
            if (L < R) {
                ans[idx] = L == 0 ? sum[R] : sum[R] - sum[L - 1];
            }
            idx++;
        }
        return ans;
    }

    public int[] platesBetweenCandles1(String s, int[][] queries) {
        if (s == null || s.length() == 0 || queries == null || queries.length == 0) {
            return new int[0];
        }
        int M = queries.length;
        int[] ans = new int[M];
        char[] str = s.toCharArray();
        int N = str.length;
        int[] left = new int[N]; // 当前i位置左侧最近的|
        int[] right = new int[N]; // 当前i位置右侧最近的|
        int[] sum = new int[N];
        int pre = -1;
        int preSum = 0;
        for (int i = 0; i < N; i++) {
            left[i] = str[i] == '|' ? i : pre;
            pre = left[i];
            sum[i] = preSum + (str[i] == '*' ? 1 : 0);
            preSum = sum[i];
        }
        pre = -1;
        for (int i = N - 1; i >= 0; i--) {
            right[i] = str[i] == '|' ? i : pre;
            pre = right[i];
        }
        for (int i = 0; i < M; i++) {
            ans[i] = query(queries[i][0], queries[i][1], left, right, sum);
        }
        return ans;
    }

    private int query(int start, int end, int[] left, int[] right, int[] sum) {
        int l = right[start]; // 当前区间内右侧开始
        int r = left[end]; // 当前区间内左侧结束
        // l == -1 or r == -1, 表示没有隔板, 答案是0
        // r<l: 不是合法的隔板封闭区间
        if (l == -1 || r == -1 || r < l) {
            return 0;
        }
        return l == 0 ? sum[r] : sum[r] - sum[l - 1];
    }

    public static void main(String[] args) {
        // String s = "**|**|***|";
        // int[][] queries = {{2,5}, {5,9}};
        // String s = "*|*||||**|||||||*||*||*||**|*|*||*";
        // int[][] queries = {{0,33}};

        // String s = "||*";
        // int[][] queries = {{2,2}};

        // String s = "*|*";
        // int[][] queries = {{2, 2}};

        String s = "***|**|*****|**||**|*";
        int[][] queries = {{1,17},{4,5},{14,17},{5,11},{15,16}}; // [9,0,0,0,0]


        var ans = new Problem_2055_PlatesBetweenCandles().platesBetweenCandles(s, queries);
        for (var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
