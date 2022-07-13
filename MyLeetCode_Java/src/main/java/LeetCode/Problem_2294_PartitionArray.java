package LeetCode;

import java.util.Arrays;

public class Problem_2294_PartitionArray {

    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int max = nums[0];
        int min = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            if (max - min > k) {
                ans++;
                max = num;
                min = num;
            }
        }
        return ans;
    }

    // 只需要维持一个极值即可
    public int partitionArray2(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 1;
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            if (num - min > k) {
                ans++;
                min = num;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
       // int[] nums = {3,6,1,2,5};
       // int k = 2;
        int[] nums = {1,2,3};
        int k = 1;
       //  int[] nums = {2,2,4,5};
       //  int k = 0;
       var ans = new Problem_2294_PartitionArray().partitionArray(nums, k);
        System.out.println(ans);
    }
}
