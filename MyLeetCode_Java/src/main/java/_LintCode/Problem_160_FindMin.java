package _LintCode;

public class Problem_160_FindMin {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if(nums[mid] < nums[R]) {
                R = mid;
            } else if (nums[mid] > nums[R]) {
                L = mid + 1;
            } else {
                R--;
            }
        }
        return nums[L];
    }
}
