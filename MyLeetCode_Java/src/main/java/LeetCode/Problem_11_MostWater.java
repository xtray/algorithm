package LeetCode;

public class Problem_11_MostWater {

    // IMP: 重要基础题!!! 背!!
    // 谁小就结算水的水量, 只关注会不会把答案推高的可能性
    // 只关注他推高答案的可能性，但是我们不去严格纠结每一个位置的值，它具体答案是多少
    // 1 100 100 2
    public int maxArea(int[] height) {
        if(height == null || height.length ==0) {
            return 0;
        }
        int N = height.length;
        int ans = 0;
        int L = 0;
        int R = N - 1;
        while (L<R) {
            ans = Math.max(ans, Math.min(height[L], height[R])*(R-L));
            if(height[L] > height[R]) { // 谁小就结算水的水量, 然后移动
                R--;
            } else {
                L++;
            }
        }
        return ans;
    }
}
