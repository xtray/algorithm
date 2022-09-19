package _Exercise;

import java.util.Arrays;

public class Problem_71_SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int N = nums.length;
        int[] cnt = new int[3];
        for (int num : nums) {
            cnt[num]++;
        }
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                nums[idx++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        new Problem_71_SortColors().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
