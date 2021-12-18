package LeetCode;

public class Problem_268_MissingNumber {

    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int R = N;
        for (int i=0; i<R;) {
            if (nums[i] <0||nums[i] >=N) {
                swap(nums, i, --R);
            } else if (nums[i] != i) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }
        for (int i=0; i<N;i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return N;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,6,4,2,3,5,7,0,1};
        Problem_268_MissingNumber sl = new Problem_268_MissingNumber();
        int ans = sl.missingNumber(arr);
        System.out.println(ans);
    }
}
