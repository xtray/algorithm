package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_1725_CountRectangles {

    public int countGoodRectangles(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0 ||
                rectangles[0] == null || rectangles[0].length == 0) {
            return 0;
        }

        // 收集最小值, 然后统计出现次数最多的
        Map<Integer, Integer> cntMap = new HashMap<>();
        int maxLen = -1;
        for (int[] rec : rectangles) {
            int len = Math.min(rec[0], rec[1]);
            cntMap.put(len, cntMap.getOrDefault(len, 0) + 1);
            maxLen = Math.max(maxLen, len);
        }
        // int num = 1;
        // for(int times:cntMap.values()) {
        //     num = Math.max(num, times);
        // }
        return cntMap.get(maxLen);
    }


    public int countGoodRectangles1(int[][] rectangles) {
        int maxLen = 0;
        int ans = 0;
        for (int[] rect : rectangles) {
            int cur = Math.min(rect[0], rect[1]);
            if (cur == maxLen) {
                ans++;
            } else if (cur > maxLen) {
                maxLen = cur;
                ans = 1; // 重新开始计数
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] rect = new int[][]{{5, 8}, {3, 9}, {3, 12}};
        var ans = new Problem_1725_CountRectangles().countGoodRectangles(rect);
        System.out.println(ans);
    }
}
