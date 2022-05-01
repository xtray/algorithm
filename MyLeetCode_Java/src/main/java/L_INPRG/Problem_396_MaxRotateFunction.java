package L_INPRG;

public class Problem_396_MaxRotateFunction {

    // NOTE: 旋转数组问题把数组复制一遍处理的技巧

    // 复制一遍数组 + 前缀和预处理
    public static int maxRotateFunction(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int[] sum = new int[N << 1];
        sum[0] = nums[0];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i % N];
        }
        int ans = 0;
        for (int i = 0; i < N; i++) { // 先形成一个窗口
            ans += nums[i] * i;
        }
        // L: 窗口左边界: N-1--> 0
        // 4326 4326
        //    N-1:
        // 从N-1位置往左移动到0位置
        int cur = ans;
        for (int L = N - 1; L >= 0; L--) {
            cur -= nums[L] * (N - 1);
            cur += sum[L + N - 1] - sum[L];
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 6};
        var ans = maxRotateFunction(nums);
        System.out.println(ans);
    }
}
