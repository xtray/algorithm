package LeetCode.Contest.DW74;

public class Problem_6020_EqualPairs {

    public boolean divideArray(int[] nums) {
        if (nums == null || nums.length == 0 || (nums.length & 1) != 0) {
            return false;
        }

        int[] cnt = new int[501];
        for(int num : nums) {
            cnt[num]++;
        }
        for(int cn: cnt) {
            if((cn&1) == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // int[] nums = {3,2,3,2,2,2};
        int[] nums = {1,2,3,4};
        var ans = new Problem_6020_EqualPairs().divideArray(nums);
        System.out.println(ans);
    }
}
