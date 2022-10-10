package LeetCode;

import java.util.Arrays;

public class Problem_75_SortColors {

    public void sortColors(int[] nums) {
        int N = nums.length;
        int zero = -1;
        int two = N;
        int i = 0;
        while (i < two) {
            if (nums[i] < 1) {
                swap(nums, i++, ++zero);
            } else if (nums[i] > 1) {
                swap(nums, i, --two);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void sortColors1(int[] nums) {
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
        int[] nums = {2,0,2,1,1,0};
        new Problem_75_SortColors().sortColors(nums);
        System.out.println(Arrays.toString(nums));

    }
}
