package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem_658_KClosestElements {

    // 根据要求排序, 取前K个
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        if (arr == null || arr.length == 0 || k < 1) {
            return ans;
        }
        for (int num : arr) {
            ans.add(num);
        }
        ans.sort((o1, o2) -> Math.abs(o1 - x) == Math.abs(o2 - x) ?
                (o1 - o2) :
                (Math.abs(o1 - x) - Math.abs(o2 - x))
        );
        k = ans.size() - k;
        while (k-- > 0) {
            ans.remove(ans.size() - 1);
        }
        ans.sort((o1, o2) -> o1 - o2);
        return ans;
    }

    // 二分
    // 先找到数组中与x差值最小的位置, 然后往两侧扩展区间
    // NOTE: 找到的最小值要往下看一位, idx+1有可能会更小(从大的一侧考虑)
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        if (arr == null || arr.length == 0 || k < 1) {
            return ans;
        }
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        int idx = -1;
        // 找到<=x离x最近的
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] <= x) {
                idx = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        // idx = idx == -1 ? 0 : idx; // 如果都>x, 就修正一下
        idx = idx == -1 ? 0 : idx; // 如果都>x, 就修正一下
        // 检查一下idx, 跟idx+1谁更近
        idx = (idx + 1) < N && Math.abs(arr[idx + 1] - x) < Math.abs(arr[idx] - x) ? idx + 1 : idx;
        // L, R是待考虑的位置
        L = idx - 1; // L可能位置不存在
        R = idx + 1;
        // NOTE: ( L...R) : 窗口大小 R-L-1个数
        while (R - L - 1 < k) {
            if (L >= 0 && R < N) {
                if (Math.abs(arr[L] - x) <= Math.abs(arr[R] - x)) {
                    L--; // 有等于号, 相等的时候优先取小的数字
                } else {
                    R++;
                }
            } else if (L >= 0) {
                L--;
            } else {
                R++;
            }
        }
        for (int i = L + 1; i <= R - 1; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }


    // 双指针: 抠掉左右两侧越界的元素
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        if (arr == null || arr.length == 0 || k < 1) {
            return ans;
        }
        int N = arr.length;
        int L = 0;
        int R = N - 1;
        int del = N - k;
        while (del-- > 0) {
            // x 在L..R左侧, 右侧, 中间都是对的, 举例子
            if (x - arr[L] <= arr[R] - x) { // 扣掉R
                R--;
            } else {
                L++;
            }
        }
        // 留下L..R就是答案
        for (int i = L; i <= R; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] arr = {1, 2, 3, 4, 5};
        // int k = 4;
        // int x = 3;

        // int[] arr = {-3, -2, 1, 2, 3};
        // int k = 4;
        // int x = -4;

        // int[] arr = {1, 1, 1, 10, 10, 10};
        // int k = 1;
        // int x = 9;

        int[] arr = {10, 10, 11, 12, 13, 14};
        int k = 3;
        int x = 9;

        var ans = new Problem_658_KClosestElements().findClosestElements(arr, k, x);
        System.out.println(ans);
        var ans1 = new Problem_658_KClosestElements().findClosestElements1(arr, k, x);
        System.out.println(ans1);
        var ans2 = new Problem_658_KClosestElements().findClosestElements2(arr, k, x);
        System.out.println(ans2);
    }
}
