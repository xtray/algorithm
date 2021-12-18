package LeetCode;

public class Problem_41_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 1;
        }

        int L = 0;
        int R = nums.length;
        while(L < R) {
            if (nums[L] <1 || nums[L] > R) {
                swap(nums, L, --R);
            } else {
                if (nums[L] == L + 1  ) {
                    L++;
                } else { //nums[L] != L + 1
                    if (nums[nums[L] -1] != nums[L]) {
                        swap(nums, L, nums[L] - 1);
                    } else {
                        swap(nums, L, --R);
                    }
                }
            }
        }

        return R +1;
    }


    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,1};
        Problem_41_FirstMissingPositive sl = new Problem_41_FirstMissingPositive();
        int ans = sl.firstMissingPositive(arr);
        System.out.println(ans);
    }
}
