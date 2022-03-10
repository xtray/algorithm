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
        if(str[0] == '|') {
            map.put(0, 1);
        }

        for(int i = 1; i< str.length;i++) {
            sum[i] += sum[i-1] + (str[i] == '*' ? 1 : 0);
            if(str[i] == '|') {
                map.put(i, 1);
            }
        }

        int[] ans = new int[queries.length];
        int idx = 0;
        for(int[] q : queries) {
            int low = q[0];
            int up = q[1];
            int L = map.ceilingKey(low) != null? map.ceilingKey(low) : str.length;
            int R = map.floorKey(up) != null ? map.floorKey(up) : -1;
            if(L < R) {
                ans[idx] = L == 0 ? sum[R] : sum[R] - sum[L-1];
            }
            idx++;
        }
        return ans;
    }

    public static void main(String[] args) {
        // String s = "**|**|***|";
        // int[][] queries = {{2,5}, {5,9}};
        // String s = "*|*||||**|||||||*||*||*||**|*|*||*";
        // int[][] queries = {{0,33}};

        // String s = "||*";
        // int[][] queries = {{2,2}};

        String s = "*|*";
        int[][] queries = {{2,2}};
        var ans = new Problem_2055_PlatesBetweenCandles().platesBetweenCandles(s, queries);
        for(var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
