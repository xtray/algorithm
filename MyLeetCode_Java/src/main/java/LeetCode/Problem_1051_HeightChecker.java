package LeetCode;

import java.util.Arrays;

public class Problem_1051_HeightChecker {

    public int heightChecker(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] arr = Arrays.copyOf(heights, heights.length);
        Arrays.sort(arr);
        int cnt = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != arr[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
