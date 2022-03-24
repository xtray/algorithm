package LeetCode;

public class Problem_189_Rotate {
    // Note: k比较大的时候, 注意取模
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return;
        }
        int N = nums.length;
        k = k % N;
        // 向右转k个, 等同于向左N-k个
        process(nums, 0, N - k - 1);
        process(nums, N - k, N - 1);
        process(nums, 0, N - 1);
    }

    private void process(int[] nums, int L, int R) {
        while (L < R) {
            int tmp = nums[L];
            nums[L++] = nums[R];
            nums[R--] = tmp;
        }
    }

    public static void main(String[] args) {
        // int[] nums = {-1, -100, 3, 99};
        // int k = 2;
        int[] nums = {1,2}; // 2,1
        int k = 3;
        Problem_189_Rotate sl = new Problem_189_Rotate();
        sl.rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }


}
