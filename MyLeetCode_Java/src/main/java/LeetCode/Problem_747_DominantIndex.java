package LeetCode;

public class Problem_747_DominantIndex {
    public int dominantIndex(int[] nums) {
        if(nums == null || nums.length<1) {
            return -1;
        }

        int max = nums[0];
        int idx = 0; // 1 个数返回 0
        int sec = Integer.MIN_VALUE;
        for(int i=1;i< nums.length;i++) {
            if(nums[i] > max) {
                sec = max;
                max = nums[i];
                idx = i;
            } else if(nums[i]<=max && nums[i] > sec) {
                sec = nums[i];
            }
        }
        return max/2 >= sec ? idx : -1;
    }
}
