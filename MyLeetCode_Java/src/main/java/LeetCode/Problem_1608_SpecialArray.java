package LeetCode;

import java.util.Arrays;

public class Problem_1608_SpecialArray {

    public int specialArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        Arrays.sort(nums);
        int L = 0;
        int R = N;
        int x = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            // >= mid 的个数
            int cnt = getCnt(nums, mid);
            if (cnt > mid) {
                L++;
            } else if (cnt < mid) {
                R--;
            } else {
                x = mid;
                break;
            }
        }
        return x;
    }

    // >= target的个数
    // 找到> target 位置数最左的
    private int getCnt(int[] nums, int target) {
        int N = nums.length;
        int pos = -1;
        int L = 0;
        int R = N - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] >= target) {
                pos = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return pos == -1 ? 0 : (N - pos);
    }
}

