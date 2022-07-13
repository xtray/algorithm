package LeetCode;

public class Problem_2221_TriangularSum {

    public int triangularSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int size = nums.length;
        while (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                nums[i] = (nums[i] + nums[i + 1]) % 10;
            }
            size--;
        }
        return nums[0];
    }

    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        int[] nums = {5};
        var ans = new Problem_2221_TriangularSum().triangularSum(nums);
        System.out.println(ans);
    }
}
