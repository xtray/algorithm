package LeetCode.Jianzhi;

import java.util.PriorityQueue;

// IMP: 非常非常重要!!!

public class Problem_JZ40_LeastNumbers {


    // 快速选择算法
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k <= 0) {
            return new int[0];
        }
        if (arr == null || arr.length <= k) {
            return arr;
        }
        // 找第K小
        process(arr, 0, arr.length - 1, k - 1);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 在L...R范围上执行快速选择算法, 返回第k小
    private int process(int[] arr, int l, int r, int index) {
        if (l == r) {
            return arr[l];
        }
        int pivot = arr[l + (int) (Math.random() * (r - l + 1))];
        int[] range = partition(arr, l, r, pivot);
        if (index < range[0]) {
            process(arr, l, range[0] - 1, index);
        } else if (index > range[1]) {
            process(arr, range[1] + 1, r, index);
        }
        return arr[index];
    }

    // arr l...r范围上做partition
    private int[] partition(int[] arr, int l, int r, int pivot) {
        int less = l - 1;
        int more = r + 1;
        int cur = l; // 盯着的数
        while (cur < more) {
            if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else { // 等于
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 优先队列
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || arr.length <= k) {
            return arr;
        }
        int N = arr.length;
        // 用大顶堆拦一个门槛
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : arr) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] ans = new int[k];
        int idx = 0;
        while (!pq.isEmpty()) {
            ans[idx++] = pq.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] arr = {3,2,1};
        // int k = 2;
        // int[] arr = {3, 2, 1, 9, 5, 4, 2, 1, 3, 6};
        // int k = 5;
        // int[] arr = {0, 0, 0, 2, 0, 5};
        // int k = 0;
        int[] arr = {0, 1, 1, 1, 4, 5, 3, 7, 7, 8, 10, 2, 7, 8, 0, 5, 2, 16, 12, 1, 19, 15, 5, 18, 2, 2, 22, 15, 8, 22, 17, 6, 22, 6, 22, 26, 32, 8, 10, 11, 2, 26, 9, 12, 9, 7, 28, 33, 20, 7, 2, 17, 44, 3, 52, 27, 2, 23, 19, 56, 56, 58, 36, 31, 1, 19, 19, 6, 65, 49, 27, 63, 29, 1, 69, 47, 56, 61, 40, 43, 10, 71, 60, 66, 42, 44, 10, 12, 83, 69, 73, 2, 65, 93, 92, 47, 35, 39, 13, 75};
        int k = 75;
        var ans = new Problem_JZ40_LeastNumbers().getLeastNumbers(arr, k);
        printArr(ans);
    }

    private static void printArr(int[] ans) {
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
