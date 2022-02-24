package AlgoExpert;

import java.util.*;

/**
 * 输出最长递增子序列数组(数据保证数组中只有一组最长的)
 */

public class Problem_000_LongestIncreasingSubsequence {

    // 最长递增子序列
    // ends 数组+ dp 表
    // dp 表可以把所有长度为最大值的递增子序列求出来
    public static List<Integer> longestIncreasingSubsequence(int[] arr) {

        if (arr == null || arr.length == 0) {
            return new ArrayList<>();
        }
        // int[] ends = new int[arr.length];
        // ends[0] = arr[0];
        List<TreeMap<Integer, Integer>> list = new ArrayList<>();
        list.add(new TreeMap<>());
        list.get(0).put(arr[0], 0);
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > list.get(m).firstKey()) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            // ends[l] = arr[i];
            if (l >= list.size()) {
                list.add(new TreeMap<>());
            }
            list.get(l).put(arr[i], i);
            max = Math.max(max, l + 1);
        }
        List<Integer> ans = new ArrayList<>();
        int size = list.size();
        ans.add(0, list.get(size - 1).firstKey());
        int pos = list.get(size -1).firstEntry().getValue();
        for (int i = size - 2; i>=0;i--) {
            while (list.get(i).firstEntry().getValue()>pos) {
                list.get(i).pollFirstEntry();
            }
            ans.add(0, list.get(i).firstKey());
            pos = list.get(i).firstEntry().getValue();
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        int[] arr = new int[]{1, 5, -1, 10};
        var ans = longestIncreasingSubsequence(arr);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
