package _Exercise;

public class Problem_268_MissingNumber {

    // 0~n
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int i = 0;
        // 0~N-1 各回各家, N跳过
        while (i < N) {
            if(nums[i] == N) {
                i++;
                continue;
            }
            if (nums[i] != i) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }
        for (i = 0; i < N; i++) {
            if (nums[i] != i) break;
        }
        return i;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
