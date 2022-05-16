package LeetCode.Jianzhi;

// IMP: 数组三连问题类似, 非常重要, 多看!!

public class Problem_JZII008_MinSubArrayLen {


    // 窗口为 [L, R) 的做法, 注意一开始sum = 0
    public static int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int N = nums.length;
        int L = 0; // 左边界
        int R = 0; // 下一个要处理的数
        int sum = 0;
        int len = N + 1; // 假定的一个最大值
        // [L, R)
        while (R < N) {
            while (R < N && sum < target) {
                sum += nums[R++];
            }
            // R == N || sum >= target
            while (L < R && sum >= target) {
                len = Math.min(len, R - L);
                sum -= nums[L++];
            }
        }
        return len == N + 1 ? 0 : len;
    }

    // 窗口为 [L, R] 的做法
    // NOTE: 这种写法很简洁
    public static int minSubArrayLen1(int target, int[] nums) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int N = nums.length;
        int L = 0; // 左边界
        int R = 0; // 下一个要处理的数
        int sum = 0;
        int len = N + 1; // 假定的一个最大值
        // [L, R]
        while (R < N) {
            sum += nums[R]; // 收集当前窗口内的新数
            // R == N || sum >= target
            while (sum >= target) {
                len = Math.min(len, R - L + 1);
                sum -= nums[L++];
            }
            R++;
        }
        return len == N + 1 ? 0 : len;
    }

    // 窗口为 [L, R] 的做法, 注意一开始sum = nums[0]
    public static int minSubArrayLen2(int target, int[] nums) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }
        int N = nums.length;
        int L = 0; // 左边界
        int R = 0; // 下一个要处理的数
        int sum = nums[0];
        int len = N + 1; // 假定的一个最大值
        while (R < N) { // 永远为true, 因为下面 R==N的时候break了
            if (sum >= target) {
                len = Math.min(len, R - L + 1);
                // L++ 缩窗口
                if (L <= R) { // NOTE: 这里必须是 <=, 只是L<R会死循环
                    sum -= nums[L]; // L位置出窗口
                    L++; // 新的窗口范围
                }
            } else { // R 往做扩
                R++;
                // if (R == N) {
                //     break;
                // }
                sum += R < N ? nums[R] : 0;
            }
        }
        return len == N + 1 ? 0 : len;
    }

    public static void main(String[] args) {
        // int target = 7; // 2
        // int[] nums = {2, 3, 1, 2, 4, 3};
        // int target = 15; // 5
        // int[] nums = {1, 2, 3, 4, 5};
        int target = 4; // 2
        int[] nums = {1, 4, 4};
        var ans = minSubArrayLen1(target, nums);
        System.out.println(ans);
    }
}
