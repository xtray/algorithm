package LeetCode.MianshiJindian;

// IMP: 比较难懂的二分, 多看!!

public class Problem_0803_MagicIndex {

    // 数组是「严格单调递增」的，就是不会有重复的数字。二分
    // 不对, 没有重复才能这样二分
    public static int findMagicIndex0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        int ans = -1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] < mid) { // 排除一个不存在的区间
                L = mid + 1;
            } else { // >=
                R = mid;
            }
        }
        if (nums[L] == L) {
            ans = L;
        }
        return ans;
    }

    // 如果数组中可以出现重复的元素
    // ref: https://leetcode.cn/problems/magic-index-lcci/solution/mo-zhu-suo-yin-by-leetcode-solution/
    public int findMagicIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        return getAnswer(nums, 0, nums.length - 1);
    }

    public int getAnswer(int[] nums, int L, int R) {
        if (L > R) {
            return -1;
        }
        int mid = L + ((R - L) >> 1);
        int leftAnswer = getAnswer(nums, L, mid - 1);
        if (leftAnswer != -1) {
            return leftAnswer;
        } else if (nums[mid] == mid) {
            return mid;
        }
        return getAnswer(nums, mid + 1, R);
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 2};
        var ans = new Problem_0803_MagicIndex().findMagicIndex(nums);
        System.out.println(ans);

    }
}
