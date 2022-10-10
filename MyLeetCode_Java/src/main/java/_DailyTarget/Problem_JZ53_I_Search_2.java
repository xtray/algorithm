package _DailyTarget;

public class Problem_JZ53_I_Search_2 {

    // 有序数组查找, 可以二分!
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        int pos = -1;
        // 找到>= target最左的位置
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] >= target) {
                pos = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        if (pos == -1 || nums[pos] != target) {
            return 0;
        }
        int cnt = 0;
        while (pos < N && nums[pos] == target) {
            cnt++;
            pos++;
        }
        return cnt;
    }

}
