package LeetCode;

public class Problem_704_Search {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] > target) {
                R--;
            } else if (nums[mid] < target) {
                L++;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
