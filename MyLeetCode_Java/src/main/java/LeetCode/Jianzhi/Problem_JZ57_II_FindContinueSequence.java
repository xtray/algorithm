package LeetCode.Jianzhi;

import java.util.ArrayList;
import java.util.List;


// IMP: 不定滑动窗口板子题, 多看!!

public class Problem_JZ57_II_FindContinueSequence {

    // 从1, 2, 3...每一个数开头的都收集
    public int[][] findContinuousSequence(int target) {
        if (target <= 0) {
            return new int[0][0];
        }
        List<int[]> ans = new ArrayList<>();
        int L = 1;
        int R = 1;
        int sum = 0;
        int limit = (target + 1) / 2;
        while (R <= limit) {
            sum += R;
            while (sum > target) {
                sum -= L++;
            }
            // 收集答案
            if (sum == target && R - L + 1 >= 2) {
                ans.add(genAns(L, R));
            }
            R++;
        }
        return ans.toArray(new int[ans.size()][]);
    }

    private int[] genAns(int l, int r) {
        int[] ans = new int[r - l + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = l + i;
        }
        return ans;
    }

    // 用L做限定的方法
    public int[][] findContinuousSequence2(int target) {
        if (target <= 0) {
            return new int[0][0];
        }
        List<int[]> ans = new ArrayList<>();
        int L = 1;
        int R = 1;
        int sum = 0;
        // [L, R)
        while (L <= target/2) {
            if (sum < target) {
                sum += R++;
            } else if (sum > target) {
                sum -= L++;
            } else {
                // 收集答案
                ans.add(genAns(L, R - 1));
                sum -= L++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }


    public static void main(String[] args) {
        int target = 15;
        var ans = new Problem_JZ57_II_FindContinueSequence().findContinuousSequence2(target);
        System.out.println();
    }
}
