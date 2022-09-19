package LeetCode;

import java.util.ArrayDeque;

public class Problem_42_TrappingWater {

    // 结算每个位置i的水量
    // i位置 水量 =  min{左边最大值, 右边最大值} - [i] 跟 0 比大的
    public int trap(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int N = height.length;
        int L = 1;
        int R = N - 2;
        int water = 0;
        int leftMax = height[0];
        int rightMax = height[N - 1];
        while (L <= R) {
            // i位置左右两侧最大值谁小结算谁的水量
            if (leftMax < rightMax) {
                water += Math.max(0, leftMax - height[L]);
                leftMax = Math.max(leftMax, height[L++]);
            } else {
                water += Math.max(0, rightMax - height[R]);
                rightMax = Math.max(rightMax, height[R--]);
            }
        }
        return water;
    }

    // 单调栈的解法
    // https://leetcode.cn/problems/trapping-rain-water/solution/zhan-xi-lie-zhi-dan-diao-zhan-zong-jie-b-dktn/
    // 1.当前高度小于等于栈顶高度，入栈
    // 2.当前高度大于栈顶高度, 弹出并结算栈顶元素(利用弹出后的栈顶墙跟到来的墙)
    // 相等的元素被压入栈里, 并不影响结算 [8,5,5,3,4,5,6]
    public int trap1(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int ans = 0;
        // 栈底-->栈顶 由大到小, 遇到元素比栈顶大 开始弹出
        ArrayDeque<Integer> stack = new ArrayDeque<>(); // 存下标
        int N = height.length;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peekLast()]) {
                int top = stack.pollLast();
                // 栈顶的墙 跟 当前遇到的墙 结算 top 位置的水位
                if (stack.isEmpty()) break; // 没有栈顶墙了, 跳出循环
                // 两堵墙的距离
                int dist = i - stack.peekLast() - 1;// 要多减1
                // 用左右墙低的那个 - 当前墙 结算
                // 困水的高度
                int wh = Math.min(height[stack.peekLast()], height[i]) - height[top];
                ans += wh * dist;
            }
            stack.addLast(i);
        }
        // 最后栈内留下的墙无法积水(从左往右, 由高到底)
        return ans;
    }

    public static void main(String[] args) {
        int[] height = {8, 5, 5, 3, 4, 5, 6}; // 8
        var ans = new Problem_42_TrappingWater().trap1(height);
        System.out.println(ans);

    }
}
