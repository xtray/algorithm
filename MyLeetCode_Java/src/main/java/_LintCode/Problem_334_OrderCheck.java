package _LintCode;

import java.util.Arrays;
import java.util.List;

public class Problem_334_OrderCheck {

    public int orderCheck0(List<Integer> heights) {
        if (heights == null || heights.size() == 0) {
            return 0;
        }
        int N = heights.size();
        // 当前i位置 >= 左侧的最大值
        // 找最右侧的X号
        int left = -1;
        int leftMax = heights.get(0);
        for (int i = 1; i < N; i++) {
            if (heights.get(i) < leftMax) {
                left = i;
            }
            leftMax = Math.max(leftMax, heights.get(i));
        }
        if (left == -1) {
            return 0;
        }
        // 当前i位置 <= 右侧最小值
        // 找最左侧的X号
        int right = -1;
        int rightMin = heights.get(N - 1);
        for (int i = N - 2; i >= 0; i--) {
            if (heights.get(i) > rightMin) {
                right = i;
            }
            rightMin = Math.min(rightMin, heights.get(i));
        }
        return left - right;
    }

    public int orderCheck(List<Integer> heights) {
        if (heights == null || heights.size() == 0) {
            return 0;
        }
        int N = heights.size();
        int[] clone = new int[N];
        for (int i = 0; i < N; i++) {
            clone[i] = heights.get(i);
        }
        Arrays.sort(clone);
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (clone[i] != heights.get(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Integer[] nums = {1, 1, 3, 3, 4, 1}; // 3
        // Integer[] nums = {98, 28, 99, 15, 38, 34, 28, 54, 20, 40}; // 9
        var ans = new Problem_334_OrderCheck().orderCheck(Arrays.asList(nums));
        System.out.println(ans);
    }
}
