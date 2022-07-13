package LeetCode.MSJD;

public class Problem_1704_MissingNum {

    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int L = 0;
        int R = nums.length;
        // N 0~N
        while (L < R) {
            if (nums[L] == R) {
                swap(nums, L, --R);
                continue;
            }
            if (nums[L] == L) {
                L++;
            } else { //nums[L] != L + 1
                swap(nums, L, nums[L]);
            }
        }
        for(int i = 0; i<R; i++) {
            if(nums[i] != i) {
                return i;
            }
        }
        return R + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {3,0,1};
        var ans = new Problem_1704_MissingNum().missingNumber(nums);
        System.out.println(ans);
    }
}
