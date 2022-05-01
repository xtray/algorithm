package LeetCode;

import java.util.PriorityQueue;

// IMP: 非常重要的基础题
// NOTE: 有序数组, 第 1 大是最后一个数(第N小) 下标 N -1, 第K大下标就是 N - K, 就是 N - K + 1 小

// ref: Problem_17_14_MinKth

public class Problem_215_FindKLargest {

    // 解法 1:使用小根堆, 围 K 个元素, 数组遍历一遍后, 顶部的数就是第 K 大
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length < k) {
            return Integer.MIN_VALUE;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num : nums) {
            if (priorityQueue.size() < k) { // 堆没有满, 直接进
                priorityQueue.add(num);
                continue;
            }
            // 堆满了
            if (!priorityQueue.isEmpty() && num > priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(num);
            }
        }
        return priorityQueue.peek();
    }

    // 解法 2: 优化的快速排序
    // 第K 大, 即为N - k + 1小的 (举例子: 1,2,3,4 N=4)
    // 下标为 N -k
    public int findKthLargest2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || nums.length < k) {
            return Integer.MIN_VALUE;
        }
        // 找 L...R 范围上的第 K 小
        return process(nums, 0, nums.length - 1, nums.length - k);
    }

    private int process(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, L, range[0] - 1, index);
        } else {
            return process(arr, range[1] + 1, R, index);
        }
    }

    // 迭代找第K小
    private int process2(int[] arr, int L, int R, int index) {
        int pivot = 0;
        int[] range = null;
        while (L < R) {
            pivot = arr[L + (int)(Math.random()*(R-L+1))];
            range = partition(arr, L, R, pivot);
            if(index < range[0]) {
                R = range[0] - 1;
            } else if(index > range[1]) {
                L = range[1] + 1;
            } else {
                return pivot;
            }
        }
        return arr[L]; // L == R只有一个数了
    }

    // 荷兰国旗划分
    private int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1; // 小于区
        int more = R + 1; // 大于区
        int i = L; // 眼睛盯着的位置
        while (i < more) {
            if (arr[i] < pivot) {
                swap(arr, ++less, i++);
            } else if (arr[i] > pivot) {
                swap(arr, --more, i);
                // i 停在原位置
            } else {
                // 相等不交换
                i++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    // 解法 3: bfprt

    public static void main(String[] args) {
        Problem_215_FindKLargest sl = new Problem_215_FindKLargest();
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        int res = sl.findKthLargest2(nums, k);
        System.out.println(res);
    }
}
