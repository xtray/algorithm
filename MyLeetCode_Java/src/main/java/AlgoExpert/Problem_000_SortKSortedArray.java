package AlgoExpert;

import java.util.PriorityQueue;

// https://www.algoexpert.io/questions/Sort%20K-Sorted%20Array
public class Problem_000_SortKSortedArray {

    public static int[] sortKSortedArray(int[] array, int k) {
        if (array == null || array.length == 0) {
            return new int[]{};
        }
        if (k > array.length - 1) {
            k = array.length - 1;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int i = 0;
        for (; i < k; i++) {
            priorityQueue.add(array[i]);
        }
        for (; i < array.length; i++) {
            priorityQueue.add(array[i]);
            int min = priorityQueue.poll();
            array[i - k] = min;
        }

        for (i = array.length - k; i < array.length; i++) {
            array[i] = priorityQueue.poll();
        }

        return array;
    }

    public static int[] sortKSortedArray2(int[] array, int k) {
        if (array == null || array.length == 0 || k == 0) {
            return array;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int i = 0;
        for (; i < Math.min(k, array.length); i++) {
            heap.add(array[i]);
        }
        int index = 0;
        for (; i < array.length; i++) {
            heap.add(array[i]);
            array[index++] = heap.poll();
        }
        while (!heap.isEmpty()) {
            array[index++] = heap.poll();
        }
        return array;
    }

    public static void main(String[] args) {
//        int[] arr = new int[] {3, 2, 1, 5, 4, 7, 6, 5};
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 1};
//        int k = 3;
        int k = 8;
        int[] ans = sortKSortedArray(arr, k);
        for (int num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
