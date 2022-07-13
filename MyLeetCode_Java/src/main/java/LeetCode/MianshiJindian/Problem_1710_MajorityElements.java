package LeetCode.MianshiJindian;

public class Problem_1710_MajorityElements {

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int candidate = nums[0];
        int HP = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candidate) {
                HP++;
            } else {
                HP--;
                if (HP == 0) {
                    candidate = nums[i];
                    HP++;
                }
            }
        }
        HP = 0;
        for (int num : nums) {
            if (num == candidate) {
                HP++;
            }
        }
        if (HP > nums.length / 2) {
            return candidate;
        }
        return -1;
    }
}
