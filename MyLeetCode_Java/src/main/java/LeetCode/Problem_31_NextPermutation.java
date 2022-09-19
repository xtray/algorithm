package LeetCode;

// IMP: 非常重要基础题
// ref: Problem_556_NextGreatNum

public class Problem_31_NextPermutation {

    // 数组右侧是最低位
    public static void nextPermutation(int[] nums) {
        int N = nums.length;
        // 从右往左第一次降序的位置
        int firstLess = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }
        if (firstLess < 0) { // 从右往左一直升序, 此时就是最大
            reverse(nums, 0, N - 1);
            return;
        }

        int rightBig = -1; // firstLess存在, 则 rightBig 必然存在
        // 找最靠右的、同时比nums[firstLess]大的数，位置在哪
        // 这里其实也可以用二分优化，但是这种优化无关紧要了
        for (int i = N - 1; i > firstLess; i--) {
            if (nums[i] > nums[firstLess]) {
                rightBig = i;
                break;
            }
        }
        swap(nums, firstLess, rightBig);
        reverse(nums, firstLess + 1, N - 1);

    }

    public static void reverse(int[] nums, int L, int R) {
        while (L < R) {
            swap(nums, L++, R--);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
