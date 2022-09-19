package LeetCode.MSJD;

public class Problem_1710_MajorityElements {

    // TAG: 众数, 摩尔投票法
    //  血量为0立候选
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int candidate = -1;
        int HP = 0;
        for (int i = 0; i < nums.length; i++) {
            if (HP == 0) {
                candidate = nums[i];
                HP++;
            } else {
                if (nums[i] == candidate) {
                    HP++;
                } else {
                    HP--;
                }
            }
        }
        if (HP == 0) {
            return -1;
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
