package LeetCode.MianshiJindian;

import java.util.PriorityQueue;

// ref: Problem_215_FindKLargest

public class Problem_17_14_MinKth {

    // IMP: 非常重要!!!
    public int[] smallestK(int[] arr, int k) {
        // NOTE: 注意 k==0 的特列
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[]{};
        }
        // 迭代找第k小
        int L = 0;
        int R = arr.length - 1;
        int pivot = 0;
        int[] range = null;
        k--; // 第1小是 index 0
        while (L < R) {
            pivot = arr[L + (int) (Math.random() * (R - L + 1))];
            range = partition(arr, L, R, pivot);
            if (k < range[0]) {
                R = range[0] - 1;
            } else if (k > range[1]) {
                L = range[1] + 1;
            } else {
                break;
            }
        }
        k++;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int i = L; // 盯着的数
        while (i < more) {
            if (arr[i] < pivot) {
                swap(arr, ++less, i++);
            } else if (arr[i] > pivot) {
                swap(arr, --more, i);
            } else { // 相等
                i++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    // 优先队列的做法
    public int[] smallestK2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k == 0) {
            return new int[]{};
        }
        if (arr.length <= k) {
            return arr;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < pq.peek()) {
                pq.poll();
                pq.offer(arr[i]);
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
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;
        var ans = new Problem_17_14_MinKth().smallestK2(arr, k);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
