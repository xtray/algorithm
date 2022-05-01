package LeetCode;

public class Problem_905_ChangeOrder {

    // NOTE: 类似快速排序partition的过程
    public int[] sortArrayByParity(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int N = nums.length;
        int even = -1; // 界外
        int odd = N; // 界外
        int cur = 0; // 盯着的位置
        //  偶数 )....(奇数
        //       ^
        //      cur
        while (cur < odd) {
            if ((nums[cur] & 1) != 0) { // 奇数, 跟奇数区前一个位置交换
                swap(nums, cur, --odd);
            } else {
                swap(nums, cur++, ++even); // 跟偶数区下一个位置交换
            }
        }
        return nums;
    }

    public void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        var ans = new Problem_905_ChangeOrder().sortArrayByParity(nums);
        printArr(ans);
    }

    private static void printArr(int[] ans) {
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
