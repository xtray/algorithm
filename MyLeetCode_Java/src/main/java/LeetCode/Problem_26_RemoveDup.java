package LeetCode;

public class Problem_26_RemoveDup {


    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int len = 1; //数组的有效长度 or 下一个新到来的数要放的位置
        for (int i = 1; i < N; ) { // i 是盯着检查的位置
            if (nums[i] == nums[len - 1]) {
                i++;
            } else {
                nums[len++] = nums[i++];
            }
        }
        return len;
    }


    public static void main(String[] args) {
        // int[] nums = {1,1,2};
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        // int[] nums = {0,1,2};
        // int[] nums = {1, 2};
        var ans = new Problem_26_RemoveDup().removeDuplicates(nums);
        System.out.println(ans);
        for (int i = 0; i < ans; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
