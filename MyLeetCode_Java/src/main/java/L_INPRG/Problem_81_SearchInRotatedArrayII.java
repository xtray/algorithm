package L_INPRG;

// IMP: 重要二分题

public class Problem_81_SearchInRotatedArrayII {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            if (nums[L] == nums[mid] && nums[mid] == nums[R]) {
                L++;
                R--;
            } else if (nums[mid] <= nums[R]) { // 说明右部分有序
                if (nums[mid] < target && target <= nums[R]) {
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            } else {
                if (nums[L] <= target && target < nums[mid]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
        }
        return false;
    }
}
