package LeetCode;

public class Problem_283_MoveZeros {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int N = nums.length;
        int L = 0; // 已经处理好的头部
        int R = 0; // 待处理的头部
        while (R < N) {
            if(nums[R] != 0) {
                swap(nums, L, R);
                L++;
            }
            R++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new Problem_283_MoveZeros().moveZeroes(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
