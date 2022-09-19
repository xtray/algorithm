package _DailyTarget;

public class Problem_JZ11_MinArray_2 {

    /**
     * 没有重复数字时
     * <p>
     * 旋转点将数组分为两段有序数组(均为从小到大), 两段均单调递增, arr[R]在第二段
     * <p>
     * 如果 arr[mid] <  arr[R]: 说明二者在同一段, 且mid位于第二段, 向左侧搜索
     * 如果 arr[mid] >  arr[R]: 说明mid位于第一段, 向右侧搜索
     * 如果 arr[mid] ==  arr[R]: 没有二段性, R--, 抠掉最后一个数, 不影响最小值求解
     */

    public int minArray(int[] arr) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < arr[R]) { // mid 的右边一定不是最小数字，mid 有可能是，下一轮搜索区间是 [L, mid]
                R = mid; // mid是一个潜在的答案
            } else if (arr[mid] > arr[R]) { // 小的在左侧
                L = mid + 1;
            } else {
                R--;
            }
        }
        return arr[L]; // 此时 L == R
    }

    // 考虑数组中的最后一个元素x：在最小值右侧的元素，它们的值一定都小于等于x；
    // 而在最小值左侧的元素，它们的值一定都大于等于x
    public int minArray1(int[] arr) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        int minVal = arr[R]; // 以数组最右侧的数作为靶子跟中间的数做比较
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < minVal) {
                minVal = arr[mid]; // 相当于立了一个新的最右侧的数
                R = mid - 1;
            } else if (arr[mid] > minVal) { // 小的在右侧
                L = mid + 1;
            } else {
                R--;
                // 抠掉相等的数, 同时尝试更新靶子
                minVal = R >= 0 ? Math.min(minVal, arr[R]) : minVal;
            }
        }
        return minVal;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 3, 5}; // 1
        // int[] nums = {3, 3, 1, 3}; // 1
        int[] nums = {1}; // 1
        // int[] nums = {2, 2, 2, 0, 1}; // 0
        var ans = new Problem_JZ11_MinArray_2().minArray1(nums);
        System.out.println(ans);

    }
}
