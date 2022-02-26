package LeetCode;

public class Problem_2016_MaxiumDifference {


    public int maximumDifference(int[] nums) {
        if(nums == null || nums.length<=1) {
            return 0;
        }
        int N = nums.length;
        int min = nums[0];
        // 以每一个 nums[i] 作为最大值
        // 找之前<i 的最小值
        int ans = -1;
        for(int i = 1; i<N;i++) {
           min = Math.min(min, nums[i-1]);
           if(nums[i] > min) {
               ans = Math.max(ans, nums[i] - min);
           }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {7,1,5,4};
        var ans = new Problem_2016_MaxiumDifference().maximumDifference(nums);
        System.out.println(ans);
    }

}
