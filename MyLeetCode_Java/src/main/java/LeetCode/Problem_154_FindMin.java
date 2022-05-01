package LeetCode;

public class Problem_154_FindMin {
    public int findMin(int[] arr) {
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
}
