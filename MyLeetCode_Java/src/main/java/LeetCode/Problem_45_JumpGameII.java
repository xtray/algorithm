package LeetCode;

// IMP: 非常重要, 多看!!

public class Problem_45_JumpGameII {

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int step = 0;
        int cur = 0; // 当前步数下能到的最远位置
        int next = 0; // 多跳一步能到的最远位置
        for (int i = 0; i < N; i++) {
            if (cur < i) { // 无法到达i, 必须要迈一步
                step++;
                cur = next;
            }
            // 每一步都看当前值能不能推高next
            next = Math.max(next, i + nums[i]);
        }
        return step;
    }
}
