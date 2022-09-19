package _Contest.LC.W296;

public class Problem_2293_MinMaxGame {

    public int minMaxGame(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int N = nums.length;
        int[] pre = nums;
        int size = N >> 1;
        int[] arr = null;
        while (size >= 1) {
            arr = new int[size];
            for (int i = 0; i < size; i += 2) {
                arr[i] = Math.min(pre[i << 1], pre[i << 1 | 1]);
            }
            for (int i = 1; i < size; i += 2) {
                arr[i] = Math.max(pre[i << 1], pre[i << 1 | 1]);
            }
            pre = arr;
            size >>= 1;
        }
        return arr[0];
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{1,3,5,2,4,8,2,2};
        // int[] nums = new int[]{3, 3, 5, 2};
        // int[] nums = new int[]{3};
        // int[] nums = new int[]{3, 1};
        // int[] nums = new int[]{4, 3, 2, 1};
        // int[] nums = new int[]{999, 939, 892, 175, 114, 464, 850, 107};//850
        int[] nums = new int[]{6332, 9089, 6622, 866, 6984, 6570, 3071, 5838, 6050, 2850, 1676, 1551, 1612, 3929, 9193, 1376};
        var ans = new Problem_2293_MinMaxGame().minMaxGame(nums);
        System.out.println(ans);
    }
}
