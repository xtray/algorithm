package LeetCode;

// IMP: 单调栈!! , 大鱼吃小鱼!!, 倒序考虑

import java.util.ArrayDeque;

public class Problem_2289_NoNDecreaseTotalSteps {


    public int totalSteps(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        // 从右往左依次考察进栈, 小压大
        // 栈顶到栈底由大到小, 栈底最大
        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            int curAns = 0;
            while (!stack.isEmpty() && stack.peekFirst()[0] < nums[i]) {
                int[] top = stack.pollFirst();
                curAns = Math.max(curAns + 1, top[1]);
            }
            stack.addFirst(new int[]{nums[i], curAns});
            ans = Math.max(ans, curAns);
        }
        return ans;
    }

    public int totalSteps2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[][] stack = new int[N][2];
        int stackSize = 0;
        int ans = 0;
        for (int i = N - 1; i >= 0; i--) {
            int curAns = 0;
            while (stackSize != 0 && stack[stackSize - 1][0] < nums[i]) {
                int[] top = stack[stackSize - 1];
                stackSize--; // 栈顶出栈
                curAns = Math.max(curAns + 1, top[1]);
            }
            // 入栈
            stack[stackSize][0] = nums[i];
            stack[stackSize++][1] = curAns;
            ans = Math.max(ans, curAns);
        }
        return ans;
    }
}
