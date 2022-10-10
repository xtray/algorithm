package _DailyTarget;

import java.util.ArrayList;
import java.util.List;

public class Problem_287_FindDupNumber {

    public int findDuplicate(int[] nums) {
        int N = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; ) {
            if (nums[i] == i + 1) {
                i++;
            } else {
                if (nums[nums[i] - 1] != nums[i]) {
                    swap(nums, nums[i] - 1, i);
                } else {
                    return nums[i];
                }
            }

        }
        return -1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int findDuplicate1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return -1;
        }
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
