package LeetCode.Jianzhi;

// ref: https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-by-leetcode-s/
// IMP: 重要二分, 多看!!

public class Problem_JZ011_MinArray {

    /**
     * 考虑数组中的最后一个元素
     * 在最小值右侧的元素，它们的值一定都小于等于 x；
     * 而在最小值左侧的元素, 它们的值一定都大于等于 x。
     * 因此，我们可以根据这一条性质，通过二分查找的方法找出最小值。
     *
     * 将中间元素arr[mid] 与 最右侧元素 arr[R] 比较, 会有三种情况
     * 1. arr[mid] < arr[R] : 说明 arr[mid] 在最小值右侧, 向左侧二分继续查找
     * 2. arr[mid] > arr[R] : 说明 arr[mid] 在最小值左侧, 向右侧二分继续找
     * 3. arr[mid] == arr[R] : 因为有重复元素, 并不知道arr[mid 在最小值左侧还是右侧
     *     但是因为 arr[R] 跟 arr[mid] 相等, 可以用 arr[mid]替代, 所以可以忽略arr[R]
     *     但是可以确定的是，把 arr[R] 舍弃掉，并不影响结果
     */
    public int minArray(int[] arr) {
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] < arr[R]) { // mid 的右边一定不是最小数字，mid 有可能是，下一轮搜索区间是 [L, mid]
                R = mid; // 一个潜在的答案
            } else if (arr[mid] > arr[R]) { // 小的在左侧
                L = mid + 1;
            } else {
                R--;
            }
        }
        return arr[L];
    }
    // https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/er-fen-jian-zhi-si-xiang-fen-zhi-si-xiang-by-liwei/

    public static void main(String[] args) {
        // int[] arr = {3, 1, 3};
        // int[] arr = {3, 3, 3};
        int[] arr = {1, 3, 5};
        var ans = new Problem_JZ011_MinArray().minArray(arr);
        System.out.println(ans);
    }
}
