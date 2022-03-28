package LeetCode;

public class Problem_303_NumArray {

    class NumArray {

        private int[] sum;

        public NumArray(int[] nums) {
            if(nums == null || nums.length ==0) {
                return;
            }
            int N = nums.length;
            sum = new int[N];
            sum[0] = nums[0];
            for(int i = 1; i<N; i++) {
                sum[i] += sum[i-1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return left == 0?sum[right]:(sum[right] - sum[left-1]);
        }
    }
}
