package LeetCode;

public class Problem_153_FindMin {
    /**
     * 没有重复数字时
     * <p>
     * 旋转点将数组分为两段有序数组(均为从小到大), 两段均单调递增, arr[R]在第二段
     * <p>
     * 如果 arr[mid] <  arr[R]: 说明二者在同一段, 且mid位于第二段, 向左侧搜索
     * 如果 arr[mid] >  arr[R]: 说明mid位于第一段, 向右侧搜索
     * 如果 arr[mid] ==  arr[R]: 没有二段性, R--, 抠掉最后一个数, 不影响最小值求解
     */

    public int findMin(int[] nums) {
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] < nums[R]) { // mid 的右边一定不是最小数字，mid 有可能是，下一轮搜索区间是 [L, mid]
                R = mid; // mid是一个潜在的答案
            } else if (nums[mid] > nums[R]) { // 小的在左侧
                L = mid + 1;
            }
        }
        return nums[L]; // 此时 L == R
    }
}
