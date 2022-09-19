package _DailyTarget;

// TAG: 滑动窗口

public class Problem_JZII009_SubarrayProductLessThanK_2 {

    // 长度为N的数组, 子数组个数为 N(N+1)/2
    // 计算以每个数结尾的子数组个数(即: 子数组的长度)
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return 0;
        }
        int N = nums.length;
        int ans = 0;
        int product = 1;
        int L = 0;
        int R = 0;
        while (R < N) {
            product *= nums[R];
            while (L <= R && product >= k) {
                // 注意: product ==1,k==1
                product /= nums[L++];
            }
            // 如果跳出循环 L>R, L需要来到R的下一个位置R+1, 且下面 R -L +1 也是0
            ans += R - L + 1;
            R++;
        }

        return ans;
    }

    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        // [1,1,1], k = 1, 按题目没有解,所以 k==1 时也是0
        // 因为nums[i]最小值是1, k==1, 没有解
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }
        int N = nums.length;
        int ans = 0;
        int product = 1;
        int L = 0;
        int R = 0;
        while (R < N) {
            product *= nums[R];
            // NOTE: L <=R
            while (product >= k) { // NOTE: basecase 去掉了k==1的情况, 否则这里会死循环
                product /= nums[L++];
            }
            ans += R - L + 1;
            R++;
        }
        return ans;
    }
}
