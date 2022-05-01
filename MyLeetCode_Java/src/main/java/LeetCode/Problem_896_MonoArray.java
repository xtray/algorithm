package LeetCode;

public class Problem_896_MonoArray {

    // NOTE: 确定增减性, 用首尾的数相减最好!!
    public boolean isMonotonic(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return true;
        }
        int N = nums.length;
        int pre = nums[0];
        int gap = nums[N - 1] - nums[0];
        for (int i = 1; i < N; i++) {
            if (gap >= 0 && nums[i] - pre < 0) { // 验证升序
                return false;
            }
            if (gap <= 0 && nums[i] - pre > 0) {
                return false;
            }
            pre = nums[i];
        }
        return true;
    }


    public boolean isMonotonic2(int[] nums) {
        boolean inc = true;
        boolean dec = true;
        int N = nums.length;
        for (int i = 0; i < N - 1; ++i) {
            if (nums[i] > nums[i + 1]) { // 降序
                inc = false; // 不是升序
            }
            if (nums[i] < nums[i + 1]) { // 升序
                dec = false; // 不是降序
            }
        }
        // 两个都是false, 表示有增右减, 不是单调的
        // 当数组都是一个数时, inc, dec不会改变
        return inc || dec;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 0};
        var ans = new Problem_896_MonoArray().isMonotonic(nums);
        System.out.println(ans);
    }
}
