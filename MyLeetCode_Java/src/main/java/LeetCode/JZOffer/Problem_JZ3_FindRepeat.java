package LeetCode.JZOffer;

import java.util.HashSet;
import java.util.Set;

public class Problem_JZ3_FindRepeat {

    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(set.contains(num)) {
                return num;
            }
            set.add(num);
        }
        return -1;
    }

    // 各回各家, 各找各妈
    public int findRepeatNumber2(int[] nums) {
        if(nums == null || nums.length ==0) {
            return -1;
        }
        // nums[i] --> nums[nums[i]]
        int idx = 0;
        int N = nums.length;
        while (idx < N) {
            if(nums[idx] == idx) {
                idx++;
            } else { // 不相等
                // nums[idx] --> nums[nums[idx]]
                if(nums[nums[idx]] != nums[idx]) {
                    swap(nums, idx, nums[idx]);
                } else {
                    return nums[idx];
                }
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
