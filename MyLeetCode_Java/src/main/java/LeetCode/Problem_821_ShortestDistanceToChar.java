package LeetCode;

import java.util.TreeSet;

public class Problem_821_ShortestDistanceToChar {

    public int[] shortestToChar(String s, char c) {
        if (s == null || s.length() == 0) {
            return new int[0];
        }
        char[] str = s.toCharArray();
        int N = str.length;
        TreeSet<Integer> treeSet = new TreeSet<>(); // 记录c出现的所有位置
        for (int i = 0; i < N; i++) {
            if (str[i] == c) {
                treeSet.add(i);
            }
        }
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            int ceil = treeSet.ceiling(i) == null ? 2 * N : treeSet.ceiling(i);
            int floor = treeSet.floor(i) == null ? -N : treeSet.floor(i);
            ans[i] = Math.min(ceil - i, i - floor);
        }
        return ans;
    }

    // NOTE: O(N) , 左右扫描的方法, 简易设置2N, -N的最值的方法
    public int[] shortestToChar2(String s, char c) {
        if (s == null || s.length() == 0) {
            return new int[0];
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] ans = new int[N];
        int idx = -N; // 给一个能搞出来的极端值
        for (int i = 0; i < N; i++) {
            if (str[i] == c) {
                idx = i; // 记录i左侧的最近的c的位置
            }
            ans[i] = i - idx;
        }
        idx = 2 * N; // 给一个最大值, 不可能超过这个
        for (int i = N - 1; i >= 0; i--) {
            if (str[i] == c) {
                idx = i; // 记录i右侧的最近的c的位置
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";
        char c = 'e';
        var ans = new Problem_821_ShortestDistanceToChar().shortestToChar(s, c);
        for (int num : ans) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

}
