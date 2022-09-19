package LeetCode;

import java.util.Arrays;

public class Problem_899_OrderQueue {

    // 把字典序最小的k个字母倒腾到最前面
    // 每次选前k个字符里字典序最小的
    // k长度数组里找第k小
    // https://leetcode.cn/problems/orderly-queue/solution/by-huang-bin-bin-7-26r5/
    public String orderlyQueue(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if(k >= 2) {
            Arrays.sort(str);
            return String.valueOf(str);
        }
        // k == 1, 轮转最多N-1回
        String minStr = s;
        for(int i = 1; i<N; i++) { // 用 1~N-1做开头
            // 每次挪第一个到最后
            minStr = minStr.substring(1) + minStr.charAt(0);
            if(minStr.compareTo(s) < 0) {
                s = minStr;
            }
        }
        return s;
    }

    public String orderlyQueue1(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return s;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        if(k >= 2) {
            Arrays.sort(str);
            return String.valueOf(str);
        }
        // k == 1, 轮转最多N-1回
        // 字符串最小表示法
        int pos = getMinPos(s);
        return s.substring(pos) + s.substring(0, pos);
    }


    /**
     * TAG: 字符串的最小表示法
     * 如果一个字符串可以循环：如0011→1001→1100→0110具有这种性质的字符串，
     * 那么它一定有一种或者多种最小表示法使得该字符串的字典序最小。
     */
    public static int getMinPos(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int i = 0; // 字符a
        int j = 1; // 字符b
        int k = 0; // offset, 0~N-1
        while (i < N && j < N && k < N) {  // k<N, 最多比较一轮
            // 比较两个字符的字典序大小
            char a = str[(i + k) % N]; // 轮转取
            char b = str[(j + k) % N];
            if (a == b) {
                k++;
            } else {
                // 找到第一个不同的位置
                if (a > b) {
                    i = i + k + 1;
                } else {
                    j = j + k + 1;
                }
                if (i == j) {
                    j++; // 跳过相等的位置
                }
                k = 0; // 重置offset, 重新开始新的一轮比较
            }
        }
        return Math.min(i, j);
    }


    public static void main(String[] args) {
        String s = "xita"; // axit
        int k = 1;
        var ans = new Problem_899_OrderQueue().orderlyQueue(s, k);
        System.out.println(ans);
    }


}
