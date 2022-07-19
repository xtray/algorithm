package LeetCode;

import java.util.Arrays;

public class Problem_1105_ShelfHeight {

    // dp[i]: 当前来到i位置
    // 当第i本书排在书架某一层第一个位置的时候，从i到最后一本书形成的最小高度
    // 暴力递归, TLE
    public int minHeightShelves(int[][] books, int shelfWidth) {
        return process(books, shelfWidth, 0);
    }

    // 当前来到第i本书, i这本书放在书架某一层第一个位置开始尝试
    // 返回从i到最后一本书形成的最小高度
    private int process(int[][] books, int shelfWidth, int i) {
        if (i == books.length) { // 来到最后, 没有书, 形成高度0
            return 0;
        }
        // 没有来到最后一本, 还有书要摆放
        // 来到某一层
        // 当前层只摆1本, 2本, ..., 直到达到当前层最大宽度shelfWidth
        int curHeight = 0; // 当前层高度是所有摆放书的最大高度
        int leftWidth = shelfWidth; // 剩余待摆放的宽度
        int minHeight = Integer.MAX_VALUE;
        // NOTE: leftWidth >= books[j][0] : 剩余的大小要能放下第j本书才行
        for (int j = i; j < books.length && leftWidth >= books[j][0]; j++) {
            leftWidth -= books[j][0]; // 摆放下标为j的数, 宽度减少
            curHeight = Math.max(curHeight, books[j][1]); // 更新高度
            int cur = curHeight + process(books, shelfWidth, j + 1);
            minHeight = Math.min(minHeight, cur);
        }
        return minHeight;
    }

    //改DP
    // dp[i]: 当前来到i位置
    // 当第i本书排在书架某一层第一个位置的时候，从i到最后一本书形成的最小高度
    public int minHeightShelves1(int[][] books, int shelfWidth) {
        int N = books.length;
        int[] dp = new int[N + 1]; // dp[N] = 0
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            int curHeight = 0; // 当前层高度是所有摆放书的最大高度
            int leftWidth = shelfWidth; // 剩余待摆放的宽度
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < N && leftWidth >= books[j][0]; j++) {
                leftWidth -= books[j][0]; // 摆放下标为j的数, 宽度减少
                curHeight = Math.max(curHeight, books[j][1]); // 更新高度
                int cur = curHeight + dp[j + 1];
                minHeight = Math.min(minHeight, cur);
            }
            dp[i] = minHeight;
        }
        return dp[0];
    }

    public int minHeightShelves2(int[][] books, int shelfWidth) {
        int N = books.length;
        int[] dp = new int[N + 1]; // dp[N] = 0
        for (int i = N - 1; i >= 0; i--) {
            int curHeight = 0; // 当前层高度是所有摆放书的最大高度
            int leftWidth = shelfWidth; // 剩余待摆放的宽度
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < N && leftWidth >= books[j][0]; j++) {
                leftWidth -= books[j][0]; // 摆放下标为j的数, 宽度减少
                curHeight = Math.max(curHeight, books[j][1]); // 更新高度
                minHeight = Math.min(minHeight, curHeight + dp[j + 1]);
            }
            dp[i] = minHeight;
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int[][] books = {{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}}; // 6
        int shelfWidth = 4;
        var ans = new Problem_1105_ShelfHeight().minHeightShelves(books, shelfWidth);
        System.out.println(ans);

        var ans1 = new Problem_1105_ShelfHeight().minHeightShelves1(books, shelfWidth);
        System.out.println(ans1);

        var ans2 = new Problem_1105_ShelfHeight().minHeightShelves2(books, shelfWidth);
        System.out.println(ans2);
    }


}
