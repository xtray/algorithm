package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_969_PancakeSort {

    public List<Integer> pancakeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return new ArrayList<>();
        }
        // 类似冒泡
        // 每次确定未排序部分最右端的元素（最大值）
        // 1. 0~N-1 上的最大值去 N-1 位置
        int N = arr.length;
        List<Integer> ans = new ArrayList<>();
        for (int end = N - 1; end >= 0; end--) { // 排序的末尾位置
            // [0~end] 上找最大值, 放到 end 位置
            int idx = 0; // 假定最大值
            // 跳出最大值的下标
            for (int j = 1; j <= end; j++) {
                if (arr[j] >= arr[idx]) { // > 或 >= 都行
                    idx = j;
                }
            }
            if (idx == end) {
                continue;
            }
            // 翻转, idx 放到 end 位置上
            reverse(arr, idx);
            reverse(arr, end);
            ans.add(idx + 1);
            ans.add(end + 1);
        }
        return ans;
    }

    // arr 0~idx 上逆序
    private void reverse(int[] arr, int idx) {
        for (int i = 0, j = idx; i < j; i++, j--) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{3,2,4,1};
        int[] arr = new int[]{1,2,3};
        var ans = new Problem_969_PancakeSort().pancakeSort(arr);
        for(int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
