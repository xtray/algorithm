package _DailyTarget;

public class Problem_35_SearchInsertPosition {


    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        int pos = -1;
        // >= t的最右位置
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] >= target) {
                pos = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return pos == -1 ? N : pos;
    }
}
